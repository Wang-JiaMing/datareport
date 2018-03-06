package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/index")
public class IndexController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping("/toPage")
    public String toPage(Model model){

        List<TbCfgDatabase> tbCfgDatabases=databaseService.findByRemoved();
        model.addAttribute("databases",tbCfgDatabases);
        return "pages/index";
    }

}
