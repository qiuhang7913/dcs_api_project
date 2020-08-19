package com.huaching.xa.campus.feign.from;

import com.huaching.xa.campus.basic.c_from.BaseFrom;

public class TestFrom extends BaseFrom {

    private String name;

    private String id;

    public TestFrom() {
    }

    public TestFrom(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
