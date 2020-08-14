package com.huaching.xa.campus.u_center.service.impl;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.huaching.xa.campus.u_center.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> test() {
        System.out.println(DynamicDataSourceContextHolder.peek());
        return  jdbcTemplate.queryForList("select * from campus_system_user");
    }
}
