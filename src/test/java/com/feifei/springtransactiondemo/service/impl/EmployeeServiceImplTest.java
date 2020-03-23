package com.feifei.springtransactiondemo.service.impl;


import com.feifei.springtransactiondemo.dto.AddEmployeeDTO;
import com.feifei.springtransactiondemo.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试员工新增接口
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    /**
     * 测试事务的传播机制为Propagation.REQUIRED
     * 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中
     * 这个事务传播属性一般用于调用者和被调用者，spring默认的事务传播机制
     *
     * 这里只要部门或者会员出错，就全部回滚，因为是处于一个事务中
     */
    @Test
    public void test1() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小盛盛")
                .setDepartmentName("盛盛部门");
        employeeService.addEmpByRequired(request);
    }

    /**
     * 测试事务的传播机制为Propagation.SUPPORTS
     * 支持当前事务（调用者），如果当前没有事务，就以非事务方式执行。
     *
     * 这个事务传播属性一般用于被调用
     *
     * 这里测试的是，会员新增没加上事务，因此导致部门新增也没加事务，出错，不会进行任何回滚
     */
    @Test
    public void test2() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小舒舒")
                .setDepartmentName("舒舒部门");
        employeeService.addEmpBySupported(request);
    }

    /**
     * 测试事务的传播机制为Propagation.MANDATORY
     * 使用当前的事务（调用者），如果当前没有事务，就抛出异常。
     * 这个事务传播属性一般用于被调用者
     * 错误异常为:
     * org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
     */
    @Test
    public void test3() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小飞飞")
                .setDepartmentName("飞飞部门");
        employeeService.addEmpByMandatory(request);
    }

    /**
     * 测试事务的传播机制为Propagation.REQUIRES_NEW
     * 不管当前（调用者）是否存在事务，被调用者都会新开一个事务，相当于被调用者都存在于自己的事务中和调用者没有关系。
     *
     * 这个事务传播属性一般用被调用者，可理解为不管如何都是走自己的事务，和调用或者被调用无关
     *
     * 这里测试的是会员出错，进行了回滚，但是不影响部门新增，因为他俩是2个各自的事务，根据事务的隔离性，因此不会相互影响
     */
    @Test
    public void test4() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小猪猪")
                .setDepartmentName("猪猪部门");
        employeeService.addEmpByRequiresNew(request);
    }

    /**
     * 测试事务的传播机制为Propagation.NOT_SUPPORTED
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     *
     * 这个属性如果放在调用者上，则是以非事务方式运行。
     * 如果放在被调用者上，则被调用方法以非事务运行，调用者如果有事务，则运行单独的事务（挂起）。
     *
     * 这里演示的是会员新增存在事务，部门的传播机制为Propagation.NOT_SUPPORTED
     * 如果2边都出错，则会员新增会回滚，部门新增成功，因为部门新增是以非事务运行的
     *
     */
    @Test
    public void test5() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小艳艳")
                .setDepartmentName("艳艳部门");
        employeeService.addEmpByNotSupported(request);
    }

    /**
     *
     * 测试事务的传播机制为Propagation.NESTED
     * 该事务的传播行为: 以非事务方式执行，如果当前存在事务，则抛出异常。
     *
     * 该事务的传播行为: 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作(新增一个事务)。
     * （这个和REQUIRED区别在于一个是加入到一个事务，一个是在嵌套事务运行）
     *
     * 这个一般主要是应用于回滚到指定的点（savePoint），而不是全部回滚，这个看具体场景
     * 大多数情况下和REQUIRED一样
     *
     * @author shixiongfei
     * @date 2020-03-23
     * @updateDate 2020-03-23
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Test
    public void test6() {
        AddEmployeeDTO request = new AddEmployeeDTO();
        request.setName("小徐徐")
                .setDepartmentName("徐徐部门");
        employeeService.addEmpByNever(request);
    }
}