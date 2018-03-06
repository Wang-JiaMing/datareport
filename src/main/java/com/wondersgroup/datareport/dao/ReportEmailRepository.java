package com.wondersgroup.datareport.dao;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface ReportEmailRepository extends JpaRepository<TbCfgReportEmail, Long> {

    List<TbCfgReportEmail> findByRemoved(String removed);

    TbCfgReportEmail findAllById(Long Id);


}
