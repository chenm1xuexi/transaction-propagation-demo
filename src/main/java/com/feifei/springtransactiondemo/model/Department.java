package com.feifei.springtransactiondemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since 
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_department")
public class Department implements Serializable {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private String departmentId;

    /**
     * 部门名称
     */
    @TableField(value = "department_name")
    private String departmentName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_DEPARTMENT_ID = "department_id";

    public static final String COL_DEPARTMENT_NAME = "department_name";
}