package com.feifei.springtransactiondemo.transaction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Service
public class ISomeService implements BaseService, InitializingBean {

    @Override
    public String doSomething() {
        return "let's do something";
    }

    @Override
    public String eat() {
        return "eat food";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("设置属性" + this.getClass().getName());
    }
}