package com.wusuiwei.jdiff.controllers;

import com.wusuiwei.jdiff.entities.ClassInfoDTO;
import com.wusuiwei.jdiff.entities.CodeDiffResultVO;
import com.wusuiwei.jdiff.services.CodeDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jdiff")
public class CodeDiffController {
    @Autowired
    CodeDiffService codeDiffService;

    @GetMapping("/git/list")
    public List<CodeDiffResultVO> getGitDiffResult(String gitUrl, String baseVersion, String nowVersion) {
        List<ClassInfoDTO> classinfos = codeDiffService.getDiffCode(gitUrl, baseVersion, nowVersion);
        return null;
    }
}
