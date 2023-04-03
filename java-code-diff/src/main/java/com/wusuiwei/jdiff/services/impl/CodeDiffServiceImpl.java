package com.wusuiwei.jdiff.services.impl;

import com.alibaba.fastjson2.JSON;
import com.wusuiwei.jdiff.commons.BusinessException;
import com.wusuiwei.jdiff.commons.BusinessExceptionEnum;
import com.wusuiwei.jdiff.config.GitConfig;
import com.wusuiwei.jdiff.entities.*;
import com.wusuiwei.jdiff.services.CodeDiffService;
import com.wusuiwei.jdiff.utils.GitUtil;
import com.wusuiwei.jdiff.utils.MethodParserUtils;
import com.wusuiwei.jdiff.utils.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodeDiffServiceImpl implements CodeDiffService {
    private String localBaseRepoDir;
    private String localNowRepoDir;
    @Autowired
    GitConfig gitConfig;

    @Override
    public List<ClassInfoDTO> getDiffCode(String gitUrl, String baseVersion, String nowVersion) {
        try {
            localBaseRepoDir = GitUtil.getLocalDir(gitUrl, gitConfig.getBaseDir(), baseVersion);
            localNowRepoDir = GitUtil.getLocalDir(gitUrl, gitConfig.getBaseDir(), nowVersion);
            Git baseGit = null;
            Git nowGit = null;
            GitUrlTypeEnum gitUrlTypeEnum = GitUtil.judgeUrlType(gitUrl);
            switch (gitUrlTypeEnum) {
                case SSH: {
                    localBaseRepoDir += GitUrlTypeEnum.SSH.getValue();
                    localNowRepoDir += GitUrlTypeEnum.SSH.getValue();
                    //原有代码git对象
                    baseGit = GitUtil.sshCloneRepository(gitUrl, localBaseRepoDir, baseVersion, gitConfig.getSshKey());
                    //现有代码git对象
                    nowGit = GitUtil.sshCloneRepository(gitUrl, localNowRepoDir, nowVersion, gitConfig.getSshKey());
                    break;
                }
                case HTTP: {
                    //原有代码git对象
                    baseGit = GitUtil.httpCloneRepository(gitUrl, localBaseRepoDir, baseVersion, gitConfig.getUsername(), gitConfig.getPassword());
                    //现有代码git对象
                    nowGit = GitUtil.httpCloneRepository(gitUrl, localNowRepoDir, nowVersion, gitConfig.getUsername(), gitConfig.getPassword());
                    break;
                }
                default: {
                    log.error("未知类型仓库地址");
                    throw new BusinessException(BusinessExceptionEnum.UNKNOWN_REPOSITY_URL);
                }
            }
            AbstractTreeIterator baseTree = GitUtil.prepareTreeParser(baseGit.getRepository(), baseVersion);
            AbstractTreeIterator nowTree = GitUtil.prepareTreeParser(nowGit.getRepository(), nowVersion);
            //获取差异代码
            List<DiffEntry> diff = nowGit.diff().setOldTree(baseTree).setNewTree(nowTree).setShowNameAndStatusOnly(true).call();
            //过滤出有效的差异代码
            List<DiffEntry> validDiffList = diff.stream()
                    //只计算java文件
                    .filter(e -> e.getNewPath().endsWith(".java"))
                    //排除测试文件
                    .filter(e -> e.getNewPath().contains("src/main/java"))
                    //只计算新增和变更文件
                    .filter(e -> DiffEntry.ChangeType.ADD.equals(e.getChangeType()) || DiffEntry.ChangeType.MODIFY.equals(e.getChangeType()))
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(validDiffList)) {
                log.info("没有需要对比的类");
                return null;
            }

            log.info("需要对比的差异类数：", validDiffList.size());
            log.info("需要对比的差异类为：", validDiffList);
            List<DiffEntryDto> diffEntryDtos = MyBeanUtils.copyList(validDiffList, DiffEntryDto.class);

            Map<String, DiffEntryDto> diffMap = diffEntryDtos.stream().collect(Collectors.toMap(DiffEntryDto::getNewPath, Function.identity()));
            log.info("需要比对的差异类为：", JSON.toJSON(diffEntryDtos));
            DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
            diffFormatter.setRepository(nowGit.getRepository());
            diffFormatter.setContext(0);
            //标记新增行或变更行要在类中打标记，此处忽略删除行
            for (DiffEntry diffClass : validDiffList) {
                //获取变更行
                EditList edits = diffFormatter.toFileHeader(diffClass).toEditList();
                if (CollectionUtils.isEmpty(edits)) {
                    continue;
                }
                //获取出新增行和变更行
                List<Edit> list = edits.stream().filter(e -> Edit.Type.INSERT.equals(e.getType()) || Edit.Type.REPLACE.equals(e.getType())).collect(Collectors.toList());
                List<ChangeLine> lines = new ArrayList<>(list.size());
                list.forEach(
                        edit -> {
                            ChangeLine build = ChangeLine.builder().startLineNum(edit.getBeginB()).endLineNum(edit.getEndB()).type(edit.getType().name()).build();
                            lines.add(build);
                        }
                );
                if (diffMap.containsKey(diffClass.getNewPath())) {
                    DiffEntryDto diffEntryDto = diffMap.get(diffClass.getNewPath());
                    diffEntryDto.setLines(lines);
                }
            }
            if (CollectionUtils.isEmpty(diffMap)) {
                return null;
            }
            List<ClassInfoDTO> list = diffEntryDtos.stream()
                    .map(item -> getClassMethods(getLocalNewPath(item.getNewPath()), getLocalOldPath(item.getNewPath()), item))
                    .filter(o->!ObjectUtils.isEmpty(o))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(list)) {
                log.info("计算出最终差异类数", list.size());
            }
            return list;
        } catch (IOException |
                 GitAPIException e) {
            e.printStackTrace();
            throw new BusinessException(BusinessExceptionEnum.GET_DIFF_CLASS_ERROR);
        }
    }

    private ClassInfoDTO getClassMethods(String oldClassFile, String mewClassFile, DiffEntryDto diffEntry) {

        //多线程获取差异方法，此处只要考虑增量代码太多的情况下，每个类都需要遍历所有方法，采用多线程方式加快速度
        String className = "";
        if (diffEntry.getNewPath().contains("src/main/java/")) {
            className = diffEntry.getNewPath().split("src/main/java/")[1].split("\\.")[0];
        }
        String moduleName = diffEntry.getNewPath().split("/")[0];
        //新增类直接标记，不用计算方法
        if (DiffEntry.ChangeType.ADD.equals(diffEntry.getChangeType())) {
            return ClassInfoDTO.builder()
                    .classFile(className)
                    .type(DiffEntry.ChangeType.ADD.name())
                    .moduleName(moduleName)
                    .lines(diffEntry.getLines())
                    .build();
        }
        List<MethodInfoDTO> diffMethods;
        //获取新类的所有方法
        List<MethodInfoDTO> newMethodInfoResults = MethodParserUtils.parseMethods(mewClassFile);
        //如果新类为空，没必要比较
        if (CollectionUtils.isEmpty(newMethodInfoResults)) {
            return null;
        }
        //获取旧类的所有方法
        List<MethodInfoDTO> oldMethodInfoResults = MethodParserUtils.parseMethods(oldClassFile);
        //如果旧类为空，新类的方法所有为增量
        if (CollectionUtils.isEmpty(oldMethodInfoResults)) {
            diffMethods = newMethodInfoResults;
        } else {   //否则，计算增量方法
            List<String> md5s = oldMethodInfoResults.stream().map(MethodInfoDTO::getMd5).collect(Collectors.toList());
            diffMethods = newMethodInfoResults.stream().filter(m -> !md5s.contains(m.getMd5())).collect(Collectors.toList());
        }
        //没有增量方法，过滤掉
        if (CollectionUtils.isEmpty(diffMethods)) {
            return null;
        }
        return ClassInfoDTO.builder()
                .classFile(className)
                .methodInfos(diffMethods)
                .type(DiffEntry.ChangeType.MODIFY.name())
                .moduleName(moduleName)
                .lines(diffEntry.getLines())
                .build();
    }

    ;

    private String getClassFilePath(String localBasePath, String filePackage) {
        StringBuilder builder = new StringBuilder(localBasePath);
        builder.append("/");
        builder.append(filePackage);
        return builder.toString();
    }

    private String getLocalNewPath(String filePackage) {
        return getClassFilePath(localNowRepoDir, filePackage);
    }

    private String getLocalOldPath(String filePackage) {
        return getClassFilePath(localBaseRepoDir, filePackage);
    }
}
