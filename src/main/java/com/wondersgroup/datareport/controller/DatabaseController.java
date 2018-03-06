package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import com.wondersgroup.datareport.service.DatabaseService;
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

    @RequestMapping("/save")
    @ResponseBody
    public Message toPage(TbCfgDatabase tbCfgDatabase) {
        Message message=new Message();
        tbCfgDatabase.setCreateDate(new Date());
        tbCfgDatabase.setSchemaName(tbCfgDatabase.getSchemaCnName().toUpperCase());
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
    public Message toPage(String id) {
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



}
