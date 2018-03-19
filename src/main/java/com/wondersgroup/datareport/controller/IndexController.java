package com.wondersgroup.datareport.controller;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.service.DatabaseService;
import com.wondersgroup.datareport.tasks.DataReport;
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
@RequestMapping("/index")
public class IndexController {

    private static String APPLICATIONNAME = "";

    public static String getAPPLICATIONNAME() {
        return APPLICATIONNAME;
    }

    public static void setAPPLICATIONNAME(String APPLICATIONNAME) {
        IndexController.APPLICATIONNAME = APPLICATIONNAME;
    }

    @Autowired
    DatabaseService databaseService;

    @RequestMapping("/setApplicationName")
    @ResponseBody
    public Message setApplicationNamevoid(String title) {
        APPLICATIONNAME = title;
        Message message=new Message();
        message.setType(true);
        return message;
    }

    @RequestMapping("/toPage")
    public String toPage(Model model) {
        List<TbCfgDatabase> tbCfgDatabases = databaseService.findByRemoved();
        if (APPLICATIONNAME == "") {
            for (TbCfgDatabase tbCfgDatabase : tbCfgDatabases) {
                if (tbCfgDatabase.getDataTitle() != null && !"".equals(tbCfgDatabase.getDataTitle())) {
                    APPLICATIONNAME = tbCfgDatabase.getDataTitle();
                    break;
                }
            }
            if (APPLICATIONNAME == "") {
                return "pages/setApplicationName";
            } else {
                model.addAttribute("title", APPLICATIONNAME);
                model.addAttribute("databases", tbCfgDatabases);
                model.addAttribute("sendMailType", DataReport.sendMailType);
                return "pages/index";
            }
        } else {
            model.addAttribute("title", APPLICATIONNAME);
            model.addAttribute("databases", tbCfgDatabases);
            model.addAttribute("sendMailType", DataReport.sendMailType);
            return "pages/index";
        }
    }

}
