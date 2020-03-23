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
@TableName(value = "t_employee")
public class Employee implements Serializable {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工id
     */
    @TableField(value = "employee_id")
    private String employeeId;

    /**
     * 员工名称
     */
    @TableField(value = "employee_name")
    private String employeeName;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private String departmentId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_EMPLOYEE_ID = "employee_id";

    public static final String COL_EMPLOYEE_NAME = "employee_name";

    public static final String COL_DEPARTMENT_ID = "department_id";
}