package com.huaching.xa.campus.code_generator;

import com.huaching.xa.campus.code_generator.config.CommonMapConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class GenComp {
    JdbcTemplate jdbcTemplate;

    GenComp(String ip, String port){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(CommonMapConfig.driverClass);
        dataSource.setUrl(CommonMapConfig.url.replace("{databaseIp}", ip).replace("{databasePort}", port));
        dataSource.setUsername(CommonMapConfig.username);
        dataSource.setPassword(CommonMapConfig.pass);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    GenComp(String ip, String port, String uname, String pass){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(CommonMapConfig.url.replace("{databaseIp}", ip).replace("{databasePort}", port));
        dataSource.setUsername(uname);
        dataSource.setPassword(pass);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    GenComp(DriverManagerDataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 生成所有表
     * @return
     */
    public void generatorCode() {
        List<String> tableNames = jdbcTemplate.queryForList(CommonMapConfig.queryAllTablesSql, String.class);
        generatorCode(tableNames);
    }

    public void generatorCode(String rootPath, List<String> tableNames) {
        generatorCode(rootPath, tableNames);
    }

    /**
     * 生成指定表
     * @param tableNames
     * @return
     */
    public void generatorCode(List<String> tableNames) {
        String rootPath = "F:\\workspace_qh\\campus_manage_fhs\\code_generator\\src\\main\\java";
        String packName = "com.huaching.xa.campus.ucenter";
        String author = "qiuhang";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (String tableName : tableNames) {
            Map<String, Object> table = jdbcTemplate.queryForMap(CommonMapConfig.querySqlTables, new Object[]{tableName});
            List<Map<String, Object>> columns = jdbcTemplate.queryForList(CommonMapConfig.querySqlColumns, new Object[]{tableName});
            GenUtils.generatorCode(table, columns, author, rootPath, packName);
        }
    }

}
