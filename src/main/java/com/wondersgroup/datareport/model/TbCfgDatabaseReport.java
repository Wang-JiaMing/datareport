package com.wondersgroup.datareport.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @projectName:dataqualitydrive
 * @packageName:com.wonders.dataqualitydrive.model
 * @authorName:wangjiaming
 * @createDate:2018-03-05
 * @editor:IntelliJ IDEA
 * @other:机构
 **/
@Entity
public class TbCfgDatabaseReport {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private TbCfgDatabase database;
    private String tableName;
    private String tableCnName;
    private String dataNumber;
    private String growNumber;
    private String growPercent;
    /**
     * 1.日统计
     * 2.周统计
     * 3.月统计
     */
    private String reportType;
    @Column(columnDefinition = "INT default 0")
    private String removed;
    private Date createDate;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getGrowNumber() {
        return growNumber;
    }

    public void setGrowNumber(String growNumber) {
        this.growNumber = growNumber;
    }

    public String getGrowPercent() {
        return growPercent;
    }

    public void setGrowPercent(String growPercent) {
        this.growPercent = growPercent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TbCfgDatabase getDatabase() {
        return database;
    }

    public void setDatabase(TbCfgDatabase database) {
        this.database = database;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCnName() {
        return tableCnName;
    }

    public void setTableCnName(String tableCnName) {
        this.tableCnName = tableCnName;
    }

    public String getDataNumber() {
        return dataNumber;
    }

    public void setDataNumber(String dataNumber) {
        this.dataNumber = dataNumber;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
