package com.feifei.springtransactiondemo.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddEmployeeDTO {

    String name;

    String departmentName;
}