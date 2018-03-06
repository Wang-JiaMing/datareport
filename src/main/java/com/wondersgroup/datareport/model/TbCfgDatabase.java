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
    private Date monitoringDate;
    private Date createDate;


    public Date getMonitoringDate() {
        return monitoringDate;
    }

    public void setMonitoringDate(Date monitoringDate) {
        this.monitoringDate = monitoringDate;
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
