package com.huaching.xa.campus.u_oauth.service;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Test1Impl{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> test() {
        System.out.println(DynamicDataSourceContextHolder.peek());
        return  jdbcTemplate.queryForList("select * from campus_system_user");
    }
}
