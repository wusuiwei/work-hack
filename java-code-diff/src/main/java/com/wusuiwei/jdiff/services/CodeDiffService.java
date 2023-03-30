package com.wusuiwei.jdiff.services;

import com.wusuiwei.jdiff.entities.ClassInfoDTO;

import java.util.List;

public interface CodeDiffService {
    List<ClassInfoDTO> getDiffCode(String gitUrl,String baseVersion,String nowVersion);
}
