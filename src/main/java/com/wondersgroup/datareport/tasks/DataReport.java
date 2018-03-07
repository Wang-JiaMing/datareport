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

    @Scheduled(cron = "0 0 0 * * ?")
    public void monitorByDay() {
        databaseService.saveReportByDay();
        try {
           // databaseService.sendMail("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 ? * MON")
    public void monitorByWeek() {
        databaseService.saveReportByWeek();
        try {
            databaseService.sendMail("2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 1 * ? ")
    public void monitorByMonths() {
        databaseService.saveReportByMonths();
        try {
            databaseService.sendMail("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
