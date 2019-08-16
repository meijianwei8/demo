package com.example.demo.quartz.job;


import com.example.demo.service.ImplClass;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoWireJob implements Job {

    @Autowired
    ImplClass implClass;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("test autowire result");
    }
}
