package com.feifei.springtransactiondemo.event;

import com.feifei.springtransactiondemo.event.entity.EmployeeEventEntity;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 员工注册事件
 *
 * @author shixiongfei
 * @date 2020-03-23
 * @since
 */
@Data
public class EmployeeRegisterEvent extends ApplicationEvent {

    private Integer level;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public EmployeeRegisterEvent(EmployeeEventEntity source, Integer level) {
        super(source);
        this.level = level;
    }
}