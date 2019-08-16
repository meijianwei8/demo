package com.example.demo.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 使用Spring管理JobBean，可以自动注入Job中的属性
 * （Job实例由Quartz管理，无法使用Spring注入属性）
 */
@Component
public class TaskJobFactory extends SpringBeanJobFactory {
    private final AutowireCapableBeanFactory capableBeanFactory;

    public TaskJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
        this.capableBeanFactory = capableBeanFactory;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

