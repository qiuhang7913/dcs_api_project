package com.huaching.xa.campus.ucenter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <p>Module: TestTable.java</p>
 * <p>des</p>
 *
 * @author qiuhang
 * @version 1.0 2020-08-20 17:12:11
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "test_table")
public class TestTable  {

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**  */
    @TableField(value = "name")
    private String name;

    /**  */
    @TableField(value = "sex")
    private String sex;


}