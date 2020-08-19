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
    /** 字典翻译值 */
    private Map<String,Object> transMap;

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

        return Objects.equals(transMap, baseVo.transMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transMap);
    }

    @Override
    public String toString() {
        return "BaseVo{" +
                "transMap=" + transMap +
                '}';
    }
}
