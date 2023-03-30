package com.wusuiwei.jdiff.controllers;

import com.wusuiwei.jdiff.entities.CodeDiffResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/git")
public class GitController {
    /**
     * 获取仓库分支列表
     */
    @GetMapping("/branches")
    public List<String> getGitBranches(@RequestParam("gitUrl") String gitUrl) {
        return null;
    }


    @GetMapping(value = "/string")
    public String getString() {
        return "哈哈哈";
    }

    @GetMapping("/list")
    public List<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("哈哈哈");
        return list;
    }

    @GetMapping("/listObject")
    public List<CodeDiffResultVO> getListObject() {
        ArrayList<CodeDiffResultVO> list = new ArrayList<>();
        CodeDiffResultVO codeDiffResultVO = new CodeDiffResultVO();
        codeDiffResultVO.setName("jack");
        list.add(codeDiffResultVO);
        return list;
    }

    @GetMapping("/object")
    public CodeDiffResultVO getObject() {
        CodeDiffResultVO codeDiffResultVO = new CodeDiffResultVO();
        codeDiffResultVO.setName("jack");
        return codeDiffResultVO;
    }

    @GetMapping("/map")
    public Map<String,String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("haell", "1121");
        return map;
    }

    @GetMapping("/void")
    public void getVoid() {
    }
}
