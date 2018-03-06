package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import com.wondersgroup.datareport.service.DatabaseService;
import com.wondersgroup.datareport.service.ReportEmailService;
import com.wondersgroup.datareport.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/email")
public class ReportEmailController {

    @Autowired
    ReportEmailService reportEmailService;

    @RequestMapping("/save")
    @ResponseBody
    public List<TbCfgReportEmail> save(TbCfgReportEmail tbCfgReportEmail) {
        reportEmailService.save(tbCfgReportEmail);
        return reportEmailService.findByRemoved();
    }

    @RequestMapping("/emailList")
    @ResponseBody
    public List<TbCfgReportEmail> emailList() {
        return reportEmailService.findByRemoved();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public List<TbCfgReportEmail> delete(String id) {
        reportEmailService.delete(id);
        return reportEmailService.findByRemoved();

    }

}
