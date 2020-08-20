package com.huaching.xa.campus.code_generator;

import com.huaching.xa.campus.basic.util.DateTool;
import com.huaching.xa.campus.code_generator.config.CommonMapConfig;
import com.huaching.xa.campus.code_generator.entity.ColumnEntity;
import com.huaching.xa.campus.code_generator.entity.TableEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.*;

public class GenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("Entity.java.vm");
        templates.add("Service.java.vm");
        templates.add("ServiceImpl.java.vm");
        templates.add("Mapper.java.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, Object> table,
                                     List<Map<String, Object>> columns,
                                     String author,
                                     String rootPath,
                                     String packRootName) {
        // 表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName") + "");
        tableEntity.setComments(table.get("tableComment") + "");
        // 表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), CommonMapConfig.javaTypeMap.get("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        // 列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, Object> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName") + "");
            columnEntity.setDataType(column.get("dataType") + "");
            columnEntity.setComments(column.get("columnComment") + "");
            columnEntity.setExtra(column.get("extra") + "");

            // 列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = CommonMapConfig.javaTypeMap.get(columnEntity.getDataType());
            attrType = StringUtils.isEmpty(attrType) ? "unknowType" : attrType;
            columnEntity.setAttrType(attrType);

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey") + "") && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }
            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        // 没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", packRootName);
        map.put("author", author);
        map.put("date", DateTool.getDataStrByLocalDateTime(LocalDateTime.now(), DateTool.FORMAT_L6));
        map.put("jdk", CommonMapConfig.javaTypeMap.get("jdk"));
        map.put("version", CommonMapConfig.javaTypeMap.get("version"));
        VelocityContext context = new VelocityContext(map);

        // 获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {

            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                // 添加到zip
                String fileName = getFileName(template, tableEntity.getClassName(), rootPath, packRootName);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                IOUtils.write(sw.toString(), outputStream, "UTF-8");
                FileUtils.writeByteArrayToFile(new File(fileName), outputStream.toByteArray());
                IOUtils.closeQuietly(sw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (!StringUtils.isEmpty(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String rootPath, String packageName) {
        String packagePath = rootPath + File.separator;
        if (!StringUtils.isEmpty(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }
        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + ".java";
        }
        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }
        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }
        if (template.contains("Mapper.java.vm")) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }
        return null;
    }

}
