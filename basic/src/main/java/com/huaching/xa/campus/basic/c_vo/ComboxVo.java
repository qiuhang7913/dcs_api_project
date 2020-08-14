package com.huaching.xa.campus.basic.c_vo;

/**
 * <p>公共combox vo</p>
 * <p>下拉框vo</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public class ComboxVo extends BaseVo {
    private String value;

    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
