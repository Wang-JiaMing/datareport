package com.wondersgroup.datareport.service;

import com.wondersgroup.datareport.dao.DatabaseReportRepository;
import com.wondersgroup.datareport.dao.DatabaseRepository;
import com.wondersgroup.datareport.dao.ReportEmailRepository;
import com.wondersgroup.datareport.emailModal.DataReportMail;
import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import com.wondersgroup.datareport.tasks.DataReport;
import com.wondersgroup.datareport.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.service
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class DatabaseService {

    @Autowired
    DatabaseRepository databaseRepository;
    @Autowired
    DatabaseReportRepository databaseReportRepository;
    @Autowired
    ReportEmailRepository reportEmailRepository;

    public List<TbCfgDatabase> findByRemoved() {
        return databaseRepository.findByRemoved("0");
    }

    @Transactional
    public void saveDatabase(TbCfgDatabase tbCfgDatabase) {
        databaseRepository.save(tbCfgDatabase);
    }

    public void deleteDatabase(Long id) {
        TbCfgDatabase tbCfgDatabase = databaseRepository.findAllById(id);
        tbCfgDatabase.setRemoved("1");
        databaseRepository.save(tbCfgDatabase);
    }

    @Transactional
    public void saveReport() {
        List<TbCfgDatabase> databases = databaseRepository.findByRemoved("0");
        for (TbCfgDatabase database : databases) {
            List<Object[]> dataContent = databaseReportRepository.getTableContent(database.getSchemaName());
            Date date = new Date();
            for (Object[] dataC : dataContent) {
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByTableNameAndRemovedOrderByCreateDateDesc(dataC[0].toString(), "0");
                TbCfgDatabaseReport tbCfgDatabaseReport = new TbCfgDatabaseReport();
                tbCfgDatabaseReport.setCreateDate(date);
                tbCfgDatabaseReport.setDatabase(database);
                tbCfgDatabaseReport.setDataNumber(dataC[2].toString());
                tbCfgDatabaseReport.setTableName(dataC[0].toString());
                tbCfgDatabaseReport.setTableCnName(dataC[1].toString());
                tbCfgDatabaseReport.setRemoved("0");
                if (oldDatabaseReport.size() > 0) {
                    Integer growTotal = Integer.valueOf(dataC[2].toString()) - Integer.valueOf(oldDatabaseReport.get(0).getDataNumber());
                    tbCfgDatabaseReport.setGrowNumber(growTotal + "");
                    if (Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) != 0) {
                        Double grow = Double.valueOf(dataC[2].toString()) / Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) - 1;
                        tbCfgDatabaseReport.setGrowPercent(grow + "");
                    } else {
                        tbCfgDatabaseReport.setGrowPercent("0.0");
                    }

                }
                databaseReportRepository.save(tbCfgDatabaseReport);

            }
            database.setMonitoringDate(date);
            databaseRepository.save(database);
        }

    }

    public void sendMail() throws Exception{
        List<TbCfgDatabase> tbCfgDatabases = databaseRepository.findByRemoved("0");
        StringBuffer htmlContent=new StringBuffer();
        htmlContent.append(DataReportMail.getHead(tbCfgDatabases));
        for (TbCfgDatabase tbCfgDatabase : tbCfgDatabases) {
            List<TbCfgDatabaseReport> tbCfgDatabaseReports = databaseReportRepository.findAllByDatabaseAndMaxCreateData(tbCfgDatabase.getId(), tbCfgDatabase.getMonitoringDate());
            htmlContent.append(DataReportMail.content(tbCfgDatabaseReports));
        }
        htmlContent.append(DataReportMail.lastHtml());
        SendMail sendMail=new SendMail();
        List<TbCfgReportEmail> tbCfgReportEmails=reportEmailRepository.findByRemoved("0");
        for (TbCfgReportEmail tbCfgReportEmail :tbCfgReportEmails) {
            if(tbCfgReportEmail.getSendType().equals("1")) {
                sendMail.sendHtmlMail("数据监控报告", htmlContent.toString(), tbCfgReportEmail.getEmailAddress());
            }
        }
    }

}
