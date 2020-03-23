package com.feifei.springtransactiondemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = {"com.feifei.springtransactiondemo.mapper"})
public class SpringTransactionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionDemoApplication.class, args);
    }

}
