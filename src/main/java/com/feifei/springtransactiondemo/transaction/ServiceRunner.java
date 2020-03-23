package com.feifei.springtransactiondemo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Component
public class ServiceRunner implements CommandLineRunner {

    @Autowired
    BaseService baseService;

    @Override
    public void run(String... args) {
        System.out.println(baseService.doSomething());
    }
}
