package com.wusuiwei.generator;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MyBatisPlugin
 * @Description: mybatis-generator注释插件
 */
public class MyBatisPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean hasLombok = Boolean.parseBoolean(properties.getProperty("hasLombok", "true"));
        if (hasLombok) {
            // 添加domain的import
            topLevelClass.addImportedType("lombok.Data");

            // 添加domain的注解
            topLevelClass.addAnnotation("@Data");
        }

        topLevelClass.addJavaDocLine("/**");

        String remarks = introspectedTable.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" * ").append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @author ").append(System.getProperties().getProperty("user.name"));
        topLevelClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @date ");
        sb.append(getDateString());
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        field.addJavaDocLine("/**");
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        field.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加Mapper的import
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));

        // 添加Mapper的注解
        interfaze.addAnnotation("@Repository");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成getter
        boolean hasLombok = Boolean.parseBoolean(properties.getProperty("hasLombok", "false"));
        return !hasLombok;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成setter
        boolean hasLombok = Boolean.parseBoolean(properties.getProperty("hasLombok", "false"));
        return !hasLombok;
    }

    protected String getDateString() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
}