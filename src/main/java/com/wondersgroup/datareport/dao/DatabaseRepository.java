package com.wondersgroup.datareport.dao;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface DatabaseRepository extends JpaRepository<TbCfgDatabase, Long> {

    List<TbCfgDatabase> findByRemoved(String removed);

    TbCfgDatabase findAllById(Long Id);
}
