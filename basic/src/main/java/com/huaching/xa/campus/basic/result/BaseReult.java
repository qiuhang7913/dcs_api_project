package com.huaching.xa.campus.basic.result;

import java.io.Serializable;

/**
 * <p>基础result</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public class BaseReult implements Serializable {

    /**
     * 返回操作code 码
     */
    private Integer code;

    /**
     * 返回操作描述
     */
    private String describe;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
