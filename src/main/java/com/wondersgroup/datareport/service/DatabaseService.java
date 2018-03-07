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
import java.util.ArrayList;
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
    public void saveReportByDay() {
        List<TbCfgDatabase> databases = databaseRepository.findByRemoved("0");
        for (TbCfgDatabase database : databases) {
            List<Object[]> dataContent = databaseReportRepository.getTableContent(database.getSchemaName());
            Date date = new Date();
            for (Object[] dataC : dataContent) {
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(dataC[0].toString(), "1", "0");
                TbCfgDatabaseReport tbCfgDatabaseReport = new TbCfgDatabaseReport();
                tbCfgDatabaseReport.setCreateDate(date);
                tbCfgDatabaseReport.setDatabase(database);
                tbCfgDatabaseReport.setDataNumber(dataC[2].toString());
                tbCfgDatabaseReport.setTableName(dataC[0].toString());
                tbCfgDatabaseReport.setTableCnName(dataC[1].toString());
                tbCfgDatabaseReport.setRemoved("0");
                tbCfgDatabaseReport.setReportType("1");
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
            database.setMonitoringDay(date);
            databaseRepository.save(database);
        }

    }

    @Transactional
    public void saveReportByWeek() {
        List<TbCfgDatabase> databases = databaseRepository.findByRemoved("0");
        for (TbCfgDatabase database : databases) {
            List<Object[]> dataContent = databaseReportRepository.getTableContent(database.getSchemaName());
            Date date = new Date();
            for (Object[] dataC : dataContent) {
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(dataC[0].toString(), "2", "0");
                TbCfgDatabaseReport tbCfgDatabaseReport = new TbCfgDatabaseReport();
                tbCfgDatabaseReport.setCreateDate(date);
                tbCfgDatabaseReport.setDatabase(database);
                tbCfgDatabaseReport.setDataNumber(dataC[2].toString());
                tbCfgDatabaseReport.setTableName(dataC[0].toString());
                tbCfgDatabaseReport.setTableCnName(dataC[1].toString());
                tbCfgDatabaseReport.setRemoved("0");
                tbCfgDatabaseReport.setReportType("2");
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
            database.setMonitoringWeek(date);
            databaseRepository.save(database);
        }

    }

    @Transactional
    public void saveReportByMonths() {
        List<TbCfgDatabase> databases = databaseRepository.findByRemoved("0");
        for (TbCfgDatabase database : databases) {
            List<Object[]> dataContent = databaseReportRepository.getTableContent(database.getSchemaName());
            Date date = new Date();
            for (Object[] dataC : dataContent) {
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(dataC[0].toString(), "3", "0");
                TbCfgDatabaseReport tbCfgDatabaseReport = new TbCfgDatabaseReport();
                tbCfgDatabaseReport.setCreateDate(date);
                tbCfgDatabaseReport.setDatabase(database);
                tbCfgDatabaseReport.setDataNumber(dataC[2].toString());
                tbCfgDatabaseReport.setTableName(dataC[0].toString());
                tbCfgDatabaseReport.setTableCnName(dataC[1].toString());
                tbCfgDatabaseReport.setRemoved("0");
                tbCfgDatabaseReport.setReportType("2");
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
            database.setMonitoringMonths(date);
            databaseRepository.save(database);
        }

    }

    public void sendMail(String reportType) throws Exception {
        List<TbCfgDatabase> tbCfgDatabases = databaseRepository.findByRemoved("0");
        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append(DataReportMail.getHead(reportType,tbCfgDatabases));
        for (TbCfgDatabase tbCfgDatabase : tbCfgDatabases) {
            Date mDate = new Date();
            if ("1".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringDay();
            } else if ("2".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringWeek();
            } else {
                mDate = tbCfgDatabase.getMonitoringMonths();
            }
            List<TbCfgDatabaseReport> tbCfgDatabaseReports = databaseReportRepository.findAllByDatabaseAndMaxCreateData(tbCfgDatabase.getId(), mDate);
            htmlContent.append(DataReportMail.content(tbCfgDatabaseReports));
        }
        htmlContent.append(DataReportMail.lastHtml());
        SendMail sendMail = new SendMail();
        List<TbCfgReportEmail> tbCfgReportEmails = reportEmailRepository.findByRemoved("0");
        for (TbCfgReportEmail tbCfgReportEmail : tbCfgReportEmails) {
            if (tbCfgReportEmail.getSendType().equals("1")) {
                sendMail.sendHtmlMail(tbCfgDatabases.get(0).getDataTitle()+"数据监控报告", htmlContent.toString(), tbCfgReportEmail.getEmailAddress());
            }
        }
    }

    public void sendAllReport(String reportType, String emailAddress) throws Exception {
        List<TbCfgDatabase> tbCfgDatabases = databaseRepository.findByRemoved("0");
        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append(DataReportMail.getHead(reportType,tbCfgDatabases));
        for (TbCfgDatabase tbCfgDatabase : tbCfgDatabases) {
            Date mDate = new Date();
            if ("1".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringDay();
            } else if ("2".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringWeek();
            } else {
                mDate = tbCfgDatabase.getMonitoringMonths();
            }
            List<TbCfgDatabaseReport> tbCfgDatabaseReports = databaseReportRepository.findAllByDatabaseAndMaxCreateData(tbCfgDatabase.getId(), mDate);
            htmlContent.append(DataReportMail.content(tbCfgDatabaseReports));
        }
        htmlContent.append(DataReportMail.lastHtml());
        SendMail sendMail = new SendMail();
        sendMail.sendHtmlMail(tbCfgDatabases.get(0).getDataTitle()+"数据监控报告", htmlContent.toString(), emailAddress);


    }

    public void sendReport(String reportType, Long id, String emailAddress) throws Exception {
        TbCfgDatabase tbCfgDatabases = databaseRepository.findAllById(id);
        List<TbCfgDatabase> tbCfgDatabaseList = new ArrayList<>();
        tbCfgDatabaseList.add(tbCfgDatabases);
        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append(DataReportMail.getHead(reportType,tbCfgDatabaseList));
        for (TbCfgDatabase tbCfgDatabase : tbCfgDatabaseList) {
            Date mDate = new Date();
            if ("1".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringDay();
            } else if ("2".equals(reportType)) {
                mDate = tbCfgDatabase.getMonitoringWeek();
            } else {
                mDate = tbCfgDatabase.getMonitoringMonths();
            }
            List<TbCfgDatabaseReport> tbCfgDatabaseReports = databaseReportRepository.findAllByDatabaseAndMaxCreateData(tbCfgDatabase.getId(), mDate);
            htmlContent.append(DataReportMail.content(tbCfgDatabaseReports));
        }
        htmlContent.append(DataReportMail.lastHtml());
        SendMail sendMail = new SendMail();
        sendMail.sendHtmlMail(tbCfgDatabases.getDataTitle()+"数据监控报告", htmlContent.toString(), emailAddress);

    }

}
