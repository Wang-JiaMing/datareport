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
public class TbCfgReportEmail {

    @Id
    @GeneratedValue
    private Long id;
    private String emailAddress;
    /**
     * 1、每天
     * 2、每周
     * 3、每月
     */
    private String sendType;
    @Column(columnDefinition="INT default 0")
    private String removed;
    private Date createDate;

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
