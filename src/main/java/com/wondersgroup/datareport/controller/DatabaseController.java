package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import com.wondersgroup.datareport.service.DatabaseService;
import com.wondersgroup.datareport.utils.Line;
import com.wondersgroup.datareport.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @projectName:dataqualitydrive
 * @packageName:com.wonders.dataqualitydrive.controller
 * @authorName:wangjiaming
 * @createDate:2018-03-02
 * @editor:IntelliJ IDEA
 * @other:算法列表
 **/
@Controller
@RequestMapping("/database")
public class DatabaseController {



    @Autowired
    DatabaseService databaseService;


    @RequestMapping("/toPage")
    public String toDataList(String id,Model model){
        List<TbCfgDatabaseReport> tbCfgDatabaseReports=databaseService.getTbCfgDatabaseReport(Long.valueOf(id));
        model.addAttribute("tbCfgDatabaseReports",tbCfgDatabaseReports);
        model.addAttribute("title",tbCfgDatabaseReports.get(0).getDatabase().getDataTitle());
        model.addAttribute("tableTitle",tbCfgDatabaseReports.get(0).getDatabase().getSchemaName());
        return "pages/tableList";
    }
    @RequestMapping("/getLine")
    @ResponseBody
    public Line getEchartLine(String id) throws Exception{
        return databaseService.getLine(Long.valueOf(id));
    }

    @RequestMapping("/init")
    @ResponseBody
    public Message init() throws Exception{
        Message message=new Message();
        try{
            databaseService.saveReportByDay();
            message.setType(true);
            message.setMsg("初始化成功");
        }catch (Exception e){
            message.setType(false);
            message.setMsg(e.getLocalizedMessage());
        }
        return message;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Message toPage(TbCfgDatabase tbCfgDatabase) {
        Message message=new Message();
        tbCfgDatabase.setDataTitle(IndexController.getAPPLICATIONNAME());
        tbCfgDatabase.setCreateDate(new Date());
        tbCfgDatabase.setSchemaName(tbCfgDatabase.getSchemaName().toUpperCase());
        tbCfgDatabase.setRemoved("0");
        try {
            databaseService.saveDatabase(tbCfgDatabase);
            message.setType(true);
        }catch (Exception e){
            e.printStackTrace();
            message.setType(false);
        }
        return message;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(String id) {
        Message message=new Message();
        try {
            databaseService.deleteDatabase(Long.valueOf(id));
            message.setType(true);
        }catch (Exception e){
            e.printStackTrace();
            message.setType(false);
        }
        return message;
    }

    @RequestMapping("/sendReport")
    @ResponseBody
    public Message sendReport(String id,String reportType,String emailAddress) {
        Message message=new Message();
        try {
            databaseService.sendReport(reportType,Long.valueOf(id),emailAddress);
            message.setType(true);
        }catch (Exception e){
            e.printStackTrace();
            message.setType(false);
        }
        return message;

    }

    @RequestMapping("/sendAllReport")
    @ResponseBody
    public Message sendReport(String reportType,String emailAddress) {
        Message message=new Message();
        try {
            databaseService.sendAllReport(reportType,emailAddress);
            message.setType(true);
        }catch (Exception e){
            e.printStackTrace();
            message.setType(false);
        }
        return message;

    }

}
