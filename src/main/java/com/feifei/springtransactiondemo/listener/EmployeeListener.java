package com.feifei.springtransactiondemo.listener;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feifei.springtransactiondemo.event.EmployeeRegisterEvent;
import com.feifei.springtransactiondemo.event.entity.EmployeeEventEntity;
import com.feifei.springtransactiondemo.model.Department;
import com.feifei.springtransactiondemo.service.DepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.feifei.springtransactiondemo.model.Department.COL_DEPARTMENT_NAME;

/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeListener {


    DepartmentService departmentService;

    /**
     * 测试 异步 + 事件监听器组合使用
     * {code condition = "#event.level > 5"} 表达式，支持 Spring el，用来做 event 中的变量或者方法判断
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Async
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level > 5")
    public void processEmployeeRegisterEvent(EmployeeRegisterEvent event) {
        System.out.println("当事务等级大于5的时候，执行此处的异步监听方法");
    }

    /**
     * 测试事务传播机制为Propagation.REQUIRED
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 1")
    public void saveDepartment1(EmployeeRegisterEvent event) {
        handle(event);
        int i = 1 / 0;
    }

    /**
     * 测试事务传播机制为Propagation.SUPPORTS
     * 如果调用者没加事务，被调用者加了事务也按照非事务方式执行
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 2")
    public void saveDepartment2(EmployeeRegisterEvent event) {
        handle(event);
        int i = 1 / 0;
    }

    /**
     * 测试事务传播机制为Propagation.MANDATORY
     * 如果调用者没加事务，则会报错
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 3")
    public void saveDepartment3(EmployeeRegisterEvent event) {
        handle(event);
        int i = 1 / 0;
    }

    /**
     * 测试事务传播机制为Propagation.REQUIRES_NEW
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 4")
    public void saveDepartment4(EmployeeRegisterEvent event) {
        handle(event);
    }

    /**
     * 测试事务传播机制为Propagation.NOT_SUPPORTED
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 5")
    public void saveDepartment5(EmployeeRegisterEvent event) {
        handle(event);
        int i = 1 / 0;
    }

    /**
     * 测试事务传播机制为Propagation.NEVER
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     */
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    @EventListener(value = {EmployeeRegisterEvent.class}, condition = "#event.level == 6")
    public void saveDepartment6(EmployeeRegisterEvent event) {
        handle(event);
        int i = 1 / 0;
    }

    /**
     * 处理用户注册事件的监听器行为
     *
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    private void handle(EmployeeRegisterEvent event) {
        EmployeeEventEntity entity = (EmployeeEventEntity) event.getSource();
        String departmentName = entity.getDepartmentName();

        Department department = departmentService.query().eq(COL_DEPARTMENT_NAME, departmentName)
                .one();

        // 新增部门信息
        if (Objects.isNull(department)) {
            department = new Department();
            department.setDepartmentName(departmentName)
                    .setDepartmentId(IdWorker.getIdStr());
            departmentService.save(department);
        }

        entity.setDepartmentId(department.getDepartmentId());
    }
}
