package com.wusuiwei.jdiff.entities;

import lombok.Data;
import org.eclipse.jgit.diff.DiffEntry;

import java.util.List;

@Data
public class DiffEntryDto {
    /**
     * 文件包名
     */
    protected String newPath;

    /**
     * 文件变更类型
     */
    private DiffEntry.ChangeType changeType;


    /**
     * 变更行
     */
    private List<ChangeLine> lines;

}
