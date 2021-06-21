package com.example.mybatisplus.core.tasks;

import com.example.mybatisplus.service.FirstVisitorDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
/*
 * 开启定时任务的注解
 */
@EnableScheduling
public class Tasks {

    @Autowired
    FirstVisitorDutyService firstVisitorDutyService;

    /*
     * 每周五23：00：00准时刷新
     * 将初访员排班表中的is_available字段设置为1
     */
    @Scheduled(cron = "0 0 23 ? * FRI")
    public void refreshFirstVisitorDuty(){
        firstVisitorDutyService.refreshDuty();
    }
}
