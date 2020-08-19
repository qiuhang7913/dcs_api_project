package com.huaching.xa.campus.basic.c_vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * <p>公共返回前站数据</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public class BaseVo implements Serializable {
<<<<<<< HEAD
    /** 字典翻译值 */
    private Map<String,Object> transMap;

=======

    /** 鉴权token */
    private String accessToken;

    /** 鉴权刷新token */
    private String refreshToken;

    /** 鉴权token失效时间 */
    private String accessTokenExpireTime;

    /** 字典翻译值 */
    private Map<String,Object> transMap;

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

>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    public Map<String, Object> getTransMap() {
        return transMap;
    }

    public void setTransMap(Map<String, Object> transMap) {
        this.transMap = transMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVo baseVo = (BaseVo) o;
<<<<<<< HEAD
        return Objects.equals(transMap, baseVo.transMap);
=======
        return Objects.equals(accessToken, baseVo.accessToken) &&
                Objects.equals(refreshToken, baseVo.refreshToken) &&
                Objects.equals(accessTokenExpireTime, baseVo.accessTokenExpireTime) &&
                Objects.equals(transMap, baseVo.transMap);
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(transMap);
=======
        return Objects.hash(accessToken, refreshToken, accessTokenExpireTime, transMap);
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    }

    @Override
    public String toString() {
        return "BaseVo{" +
<<<<<<< HEAD
                "transMap=" + transMap +
=======
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessTokenExpireTime='" + accessTokenExpireTime + '\'' +
                ", transMap=" + transMap +
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
                '}';
    }
}
