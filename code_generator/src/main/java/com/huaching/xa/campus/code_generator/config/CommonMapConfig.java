package com.huaching.xa.campus.code_generator.config;

import java.util.HashMap;
import java.util.Map;

public class CommonMapConfig {
    public static String driverClass = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://{databaseIp}:{databasePort}/test?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    public static String username = "root";
    public static String pass = "huaching123";

    public static String queryAllTablesSql = "select table_name tableName from information_schema.tables   where table_schema = (SELECT DATABASE())";
    public static String querySqlTables = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables  " +
            " where table_schema = (SELECT DATABASE()) and table_name = ?";
    public static String querySqlColumns = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns  " +
            " where table_name = ? and table_schema = (SELECT DATABASE())  order by ordinal_position ";

    /** 状态编码转换 */
    public static Map<String, String> javaTypeMap = new HashMap<String, String>();

    static {
        initJavaTypeMap();
    }

    /**
     * 返回状态映射
     */
    public static void initJavaTypeMap() {
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("mediumint", "Integer");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("integer", "integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "Double");
        // javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("time", "LocalDateTime");
        javaTypeMap.put("date", "LocalDate");
        javaTypeMap.put("datetime", "LocalDateTime");
        javaTypeMap.put("timestamp", "LocalDateTime");

        javaTypeMap.put("tablePrefix", "");// 前缀
        //javaTypeMap.put("author", "qiuhang");//作者
        javaTypeMap.put("jdk", "1.8");
        javaTypeMap.put("version", "1.0");
    }

}
