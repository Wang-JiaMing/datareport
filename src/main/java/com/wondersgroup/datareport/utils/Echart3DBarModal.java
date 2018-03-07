package com.wondersgroup.datareport.utils;

import java.util.Date;
import java.util.List;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.utils
 * @authorName:wangjiaming
 * @createDate:2018-03-07
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class Echart3DBarModal {
    public List<Date> time;
    public List<String> tableName;
    public List<List<String>> data;

    public List<Date> getTime() {
        return time;
    }

    public void setTime(List<Date> time) {
        this.time = time;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
