package com.wusuiwei.jdiff.services.impl;

import com.wusuiwei.jdiff.entities.ClassInfoDTO;
import com.wusuiwei.jdiff.services.CodeDiffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeDiffServiceImpl implements CodeDiffService {
    @Override
    public List<ClassInfoDTO> getDiffCode(String gitUrl, String baseVersion, String nowVersion) {

        return null;
    }
}
