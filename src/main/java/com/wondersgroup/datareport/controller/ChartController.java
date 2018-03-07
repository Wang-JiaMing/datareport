package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import com.wondersgroup.datareport.service.DatabaseService;
import com.wondersgroup.datareport.utils.Echart3DBarModal;
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
@RequestMapping("/echart")
public class ChartController {


    @Autowired
    DatabaseService databaseService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Echart3DBarModal setApplicationNamevoid(String id) {
        return databaseService.fidAll(id);
    }

}
