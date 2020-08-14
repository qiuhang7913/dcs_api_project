package com.huaching.xa.campus.basic.c_vo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>树形数据封装 vo</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@Data
@ToString
public class TreeNodeVo extends BaseVo {

    /** 列表树节点上的文本 */
    private String text;

    /** 列表树节点上的标识 */
    private String treeId;

    /** 父标识 */
    private String treeParentId;

    /** 结合全局enableLinks选项为列表树节点指定URL */
    private String href;

    /** 子集 */
    private List<TreeNodeVo> nodes;

    public TreeNodeVo(){

    }

    public TreeNodeVo(String text, String treeId, String treeParentId, String href){
        this.text = text;
        this.treeId = treeId;
        this.treeParentId = treeParentId;
        this.href = href;
        this.nodes = new ArrayList<>();
    }
}
