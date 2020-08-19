package com.huaching.xa.campus.u_oauth.vo;


import com.huaching.xa.campus.basic.c_vo.BaseVo;

<<<<<<< HEAD
/**
 * <p>TOKEN 返回封装</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public class TokenVo extends BaseVo {

    /** 鉴权token */
    private String accessToken;

    /** 鉴权刷新token */
    private String refreshToken;

    /** 鉴权token失效时间 */
    private String accessTokenExpireTime;

=======
import java.util.List;
import java.util.Map;

public class TokenVo extends BaseVo {
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    private Integer errorCode;

    private String errorMsg;

    private String refSchool;

<<<<<<< HEAD
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

=======
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
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
