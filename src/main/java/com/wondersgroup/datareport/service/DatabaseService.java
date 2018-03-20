package com.wondersgroup.datareport.service;

import com.wondersgroup.datareport.dao.DatabaseReportRepository;
import com.wondersgroup.datareport.dao.DatabaseRepository;
import com.wondersgroup.datareport.dao.ReportEmailRepository;
import com.wondersgroup.datareport.emailModal.DataReportMail;
import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import com.wondersgroup.datareport.utils.DateUtil;
import com.wondersgroup.datareport.utils.Line;
import com.wondersgroup.datareport.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            double totalNumber=0;
            List<Object[]> dataContent = databaseReportRepository.getTableContent(database.getSchemaName());
            Date date = new Date();
            for (Object[] dataC : dataContent) {
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByDatabaseAndTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(database,dataC[0].toString(), "1", "0");
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
                    totalNumber+=Double.valueOf(growTotal);
                    tbCfgDatabaseReport.setGrowNumber(growTotal + "");
                    if (Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) != 0) {
                        DecimalFormat df = new DecimalFormat("#.00");
                        Double grow = (Double.valueOf(dataC[2].toString()) / Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) - 1) * 100;
                        tbCfgDatabaseReport.setGrowPercent(df.format(grow));
                    } else {
                        tbCfgDatabaseReport.setGrowPercent("0.0");
                    }

                }
                databaseReportRepository.save(tbCfgDatabaseReport);

            }
            database.setGrowNumber(totalNumber);
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
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByDatabaseAndTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(database,dataC[0].toString(), "2", "0");
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
                        DecimalFormat df = new DecimalFormat("#.00");
                        Double grow = (Double.valueOf(dataC[2].toString()) / Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) - 1)*100;
                        tbCfgDatabaseReport.setGrowPercent(df.format(grow));
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
                List<TbCfgDatabaseReport> oldDatabaseReport = databaseReportRepository.findByDatabaseAndTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(database,dataC[0].toString(), "3", "0");
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
                        DecimalFormat df = new DecimalFormat("#.00");
                        Double grow = (Double.valueOf(dataC[2].toString()) / Double.valueOf(oldDatabaseReport.get(0).getDataNumber()) - 1)*100;
                        tbCfgDatabaseReport.setGrowPercent(df.format(grow));
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
        htmlContent.append(DataReportMail.getHead(reportType, tbCfgDatabases));
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
                sendMail.sendHtmlMail(tbCfgDatabases.get(0).getDataTitle() + "数据监控报告", htmlContent.toString(), tbCfgReportEmail.getEmailAddress());
            }
        }
    }

    public void sendAllReport(String reportType, String emailAddress) throws Exception {
        List<TbCfgDatabase> tbCfgDatabases = databaseRepository.findByRemoved("0");
        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append(DataReportMail.getHead(reportType, tbCfgDatabases));
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
        sendMail.sendHtmlMail(tbCfgDatabases.get(0).getDataTitle() + "数据监控报告", htmlContent.toString(), emailAddress);
    }

    public void sendReport(String reportType, Long id, String emailAddress) throws Exception {
        TbCfgDatabase tbCfgDatabases = databaseRepository.findAllById(id);
        List<TbCfgDatabase> tbCfgDatabaseList = new ArrayList<>();
        tbCfgDatabaseList.add(tbCfgDatabases);
        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append(DataReportMail.getHead(reportType, tbCfgDatabaseList));
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
        sendMail.sendHtmlMail(tbCfgDatabases.getDataTitle() + "数据监控报告", htmlContent.toString(), emailAddress);
    }

    public List<TbCfgDatabaseReport> getTbCfgDatabaseReport(Long id){
        TbCfgDatabase tbCfgDatabases = databaseRepository.findAllById(id);
        return databaseReportRepository.findAllByDatabaseAndMaxCreateDataOrderByDataNumberDesc(tbCfgDatabases.getId(), tbCfgDatabases.getMonitoringDay());
    }


    public Line getLine(Long id) throws Exception{
        Line line=new Line();
        TbCfgDatabaseReport tbCfgDatabaseReport= databaseReportRepository.findAllById(id);
        TbCfgDatabase tbCfgDatabase=tbCfgDatabaseReport.getDatabase();
        List<TbCfgDatabaseReport> tbCfgDatabaseReports=databaseReportRepository.findAllByTableNameAndDatabaseOrderByCreateDateAsc(tbCfgDatabaseReport.getTableName(),tbCfgDatabase);
        List<String> xAxisData=new ArrayList<>();
        List<String> seriesData=new ArrayList<>();
        for(TbCfgDatabaseReport tbCfgDatabaseReport1:tbCfgDatabaseReports){
            xAxisData.add(new String(DateUtil.format(tbCfgDatabaseReport1.getCreateDate(),"yyyy-MM-dd HH:mm:ss")));
            if(tbCfgDatabaseReport1.getGrowNumber()!=null&&!"".equals(tbCfgDatabaseReport1.getGrowNumber())) {
                seriesData.add(tbCfgDatabaseReport1.getGrowNumber());
            }else{
                seriesData.add("0");
            }
        }
        line.setLegendData(tbCfgDatabaseReport.getTableName());
        line.setSeriesData(seriesData);
        line.setxAxisData(xAxisData);
        return line;
    }

}
