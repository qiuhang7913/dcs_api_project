package com.huaching.xa.campus.u_oauth.vo;


import com.huaching.xa.campus.basic.c_vo.BaseVo;

import java.util.List;
import java.util.Map;

public class TokenVo extends BaseVo {
    private Integer errorCode;

    private String errorMsg;

    private String refSchool;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRefSchool() {
        return refSchool;
    }

    public void setRefSchool(String refSchool) {
        this.refSchool = refSchool;
    }
}
