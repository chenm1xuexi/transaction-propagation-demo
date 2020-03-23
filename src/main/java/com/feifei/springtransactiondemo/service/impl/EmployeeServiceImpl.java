package com.feifei.springtransactiondemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feifei.springtransactiondemo.dto.AddEmployeeDTO;
import com.feifei.springtransactiondemo.event.EmployeeRegisterEvent;
import com.feifei.springtransactiondemo.event.entity.EmployeeEventEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feifei.springtransactiondemo.model.Employee;
import com.feifei.springtransactiondemo.mapper.EmployeeMapper;
import com.feifei.springtransactiondemo.service.EmployeeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本示例只采用mysql的默认的隔离级别，可重复读来进行测试。
 * 如果要结合隔离级别 + 事务传播机制联合使用请自行测试
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    ApplicationEventPublisher eventPublisher;

    /**
     * 添加员工的同时添加部门
     * REQUIRED 传播
     * 该事务的传播行为：如果当前没有事务，则新建一个事务，如果存在，则加入到该事务中, 此为spring默认的事务传播行为
     *
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmpByRequired(AddEmployeeDTO request) {
        handle(request, 1);
        // int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * SUPPORTS 传播
     * 该事务的传播行为: 支持当前事务，如果当前没有事务，就以非事务方式执行(针对被调用者)。
     *
     *
     */
    @Override
    // @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addEmpBySupported(AddEmployeeDTO request) {
        handle(request, 2);
        int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * MANDATORY 传播
     * 该事务的传播行为: 使用当前的事务(针对被调用者)，如果当前没有事务，就抛出异常。
     *
     */
    @Override
    // @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addEmpByMandatory(AddEmployeeDTO request) {
        handle(request, 3);
        int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * REQUIRES_NEW 传播
     * 该事务的传播行为: 不管调用者是否存在事务，被调用者都会新开一个事务，相当于被调用者都存在于自己的事务中和调用者没有关系。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addEmpByRequiresNew(AddEmployeeDTO request) {
        handle(request, 4);
        int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * NOT_SUPPORTED 传播
     * 该事务的传播行为: 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     *
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmpByNotSupported(AddEmployeeDTO request) {
        handle(request, 5);
        int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * NEVER 传播
     * 该事务的传播行为: 以非事务方式执行，如果当前存在事务，则抛出异常。
     *
     */
    @Override
    // @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmpByNever(AddEmployeeDTO request) {
        handle(request, 6);
        int i = 1 / 0;
    }

    /**
     * 添加员工的同时添加部门
     * NESTED 传播(嵌套传播)
     * 该事务的传播行为: 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作(新增一个事务)。
     * （这个和REQUIRED区别在于一个是加入到一个事务，一个是在嵌套事务运行）
     *
     * 这个一般主要是应用于回滚到指定的点（savePoint），而不是全部回滚，这个看具体场景
     * 大多数情况下和REQUIRED一样
     *
     */
    @Override
    // @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmpByNested(AddEmployeeDTO request) {
        handle(request, 6);
    }

    /**
     * 添加员工注册事件处理方法
     *
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    private void handle(AddEmployeeDTO request, int level) {
        // 发布一个员工注册事件，部门监听此事件，不存在此部门则新增，存在则返回部门id
        EmployeeEventEntity entity = new EmployeeEventEntity();
        entity.setDepartmentName(request.getDepartmentName());
        eventPublisher.publishEvent(new EmployeeRegisterEvent(entity, level));

        // 新增员工信息
        Employee employee = new Employee();
        employee.setEmployeeId(IdWorker.getIdStr())
                .setEmployeeName(request.getName())
                .setDepartmentId(entity.getDepartmentId());
        save(employee);
    }
}
