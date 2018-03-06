package com.wondersgroup.datareport.service;

import com.wondersgroup.datareport.dao.DatabaseReportRepository;
import com.wondersgroup.datareport.dao.DatabaseRepository;
import com.wondersgroup.datareport.dao.ReportEmailRepository;
import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import com.wondersgroup.datareport.model.TbCfgReportEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.service
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class ReportEmailService {

    @Autowired
    ReportEmailRepository reportEmailRepository;

    public void save(TbCfgReportEmail tbCfgReportEmail){
        tbCfgReportEmail.setRemoved("0");
        tbCfgReportEmail.setCreateDate(new Date());
        reportEmailRepository.save(tbCfgReportEmail);
    }

    public List<TbCfgReportEmail> findByRemoved(){
        return reportEmailRepository.findByRemoved("0");
    }

    @Transactional
    public void delete(String id){
        TbCfgReportEmail tbCfgReportEmail=reportEmailRepository.findAllById(Long.valueOf(id));
        tbCfgReportEmail.setRemoved("1");
        reportEmailRepository.save(tbCfgReportEmail);
    }



}
