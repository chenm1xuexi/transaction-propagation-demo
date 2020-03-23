package com.feifei.springtransactiondemo.service;

import com.feifei.springtransactiondemo.dto.AddEmployeeDTO;
import com.feifei.springtransactiondemo.model.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
public interface EmployeeService extends IService<Employee> {

    void addEmpByRequired(AddEmployeeDTO request);

    void addEmpBySupported(AddEmployeeDTO request);

    void addEmpByMandatory(AddEmployeeDTO request);

    void addEmpByRequiresNew(AddEmployeeDTO request);

    void addEmpByNotSupported(AddEmployeeDTO request);

    void addEmpByNever(AddEmployeeDTO request);

    void addEmpByNested(AddEmployeeDTO request);
}