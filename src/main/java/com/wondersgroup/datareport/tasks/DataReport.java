package com.wondersgroup.datareport.tasks;

import com.wondersgroup.datareport.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.tasks
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Component
public class DataReport {

    @Autowired
    DatabaseService databaseService;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void monitor() {
        System.out.println("======进入报告======");
        databaseService.saveReport();
        System.out.println("======结束报告======");
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void sendMail() throws Exception {
        databaseService.sendMail();
    }
}
