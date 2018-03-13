package com.wondersgroup.datareport.emailModal;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.emailModal
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class DataReportMail {

    public static String getHead(String reportType,List<TbCfgDatabase> tbCfgDatabases) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String time = sdf.format(new Date());
        if(reportType.equals("1")){
            time=time+"数据日报告";
        }else if(reportType.equals("2")){
            time=time+"数据周报告";
        }else if(reportType.equals("3")){
            time=time+"数据月报告";
        }
        StringBuffer nameb = new StringBuffer();
        for (TbCfgDatabase name : tbCfgDatabases) {
            nameb.append(name.getSchemaName() + " ");
        }

        String html = "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\"\n" +
                "      lang=\"zh-CN\">\n" +
                "\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "    <script src=\"http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js\"></script>\n" +
                "    <script src=\"http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"jumbotron\" style=\"background-color: #623552\">\n" +
                "    <div class=\"container\" style=\"color: white\">\n" +
                "        <h1>" + time + "</h1>\n" +
                "        <br/>\n" +
                "        <p>以下是报告内容包括：" + nameb.toString() + "</p>\n" +
                "    </div>\n" +
                "</div>";
        return html;
    }

    public static String content(List<TbCfgDatabaseReport> tbCfgDatabaseReports) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"container\">\n" +
                "    <div class=\"panel panel-default\">\n" +
                "        <div class=\"panel-heading\">\n" +
                "            " + tbCfgDatabaseReports.get(0).getDatabase().getSchemaName() +"["+tbCfgDatabaseReports.get(0).getDatabase().getSchemaCnName()+"]\n" +
                "        </div>\n" +
                "        <div class=\"panel-body\">\n" +
                "            <table class=\"table table-hover\">\n" +
                "                <tr>\n" +
                "                    <th>表名</th>\n" +
                "                    <th>表中文名</th>\n" +
                "                    <th>当前统计数据量</th>\n" +
                "                    <th>增长量</th>\n" +
                "                    <th>增长百分比</th>\n" +
                "                </tr>\n");

        for (TbCfgDatabaseReport tbCfgDatabaseReport : tbCfgDatabaseReports) {
            stringBuffer.append("<tr>" +
                    "<td>" + tbCfgDatabaseReport.getTableName() + "</td>" +
                    "<td>" + tbCfgDatabaseReport.getTableCnName() + "</td>" +
                    "<td>" + tbCfgDatabaseReport.getDataNumber() + "</td>" +
                    "<td>" + tbCfgDatabaseReport.getGrowNumber() + "</td>" +
                    "<td>" + tbCfgDatabaseReport.getGrowPercent() + "</td>" +
                    "</tr>");
        }

        stringBuffer.append("            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>");
        return stringBuffer.toString();
    }

    public static String lastHtml() {
        return "</body></html>";
    }

}
