package com.wondersgroup.datareport.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @projectName:dataqualitydrive
 * @packageName:com.wonders.dataqualitydrive.model
 * @authorName:wangjiaming
 * @createDate:2018-03-05
 * @editor:IntelliJ IDEA
 * @other:机构
 **/
@Entity
public class TbCfgDatabase{

    @Id
    @GeneratedValue
    private Long id;
    private String schemaName;
    private String schemaCnName;
    @Column(columnDefinition="INT default 0")
    private String removed;
    private Date monitoringDay;
    private Date monitoringWeek;
    private Date monitoringMonths;
    private Date createDate;
    private String dataTitle;
    private Double growNumber;

    public Double getGrowNumber() {
        return growNumber;
    }

    public void setGrowNumber(Double growNumber) {
        this.growNumber = growNumber;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public Date getMonitoringDay() {
        return monitoringDay;
    }

    public void setMonitoringDay(Date monitoringDay) {
        this.monitoringDay = monitoringDay;
    }

    public Date getMonitoringWeek() {
        return monitoringWeek;
    }

    public void setMonitoringWeek(Date monitoringWeek) {
        this.monitoringWeek = monitoringWeek;
    }

    public Date getMonitoringMonths() {
        return monitoringMonths;
    }

    public void setMonitoringMonths(Date monitoringMonths) {
        this.monitoringMonths = monitoringMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaCnName() {
        return schemaCnName;
    }

    public void setSchemaCnName(String schemaCnName) {
        this.schemaCnName = schemaCnName;
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
