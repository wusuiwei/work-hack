package com.wusuiwei.jdiff.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
