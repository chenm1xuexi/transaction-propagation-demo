package com.feifei.springtransactiondemo.service.impl;

import org.springframework.stereotype.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feifei.springtransactiondemo.model.Department;
import com.feifei.springtransactiondemo.mapper.DepartmentMapper;
import com.feifei.springtransactiondemo.service.DepartmentService;


/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {



}