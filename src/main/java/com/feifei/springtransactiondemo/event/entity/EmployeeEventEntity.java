package com.feifei.springtransactiondemo.event.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


/**
 * 员工注册事件传输实体
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEventEntity {

    String departmentId;

    String departmentName;
}