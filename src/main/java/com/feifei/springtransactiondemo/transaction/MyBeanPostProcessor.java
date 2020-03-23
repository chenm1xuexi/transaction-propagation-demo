package com.feifei.springtransactiondemo.transaction;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/**
 * 创建自定义的后置处理器
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor, InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == ISomeService.class) {
            System.out.println("bean 实例初始化之前执行...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if (beanClass == ISomeService.class) {
            Object proxyBean = Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("bean 实例初始化之后执行...");
                String result = (String) method.invoke(bean, args);
                return result.toUpperCase();
            });
            return proxyBean;
        }
        return bean;

    }


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == ISomeService.class) {
            System.out.println("bean 实例化前执行");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == ISomeService.class) {
            System.out.println("bean 实例化后执行");
        }
        return true;
    }
}