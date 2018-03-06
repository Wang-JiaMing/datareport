package com.wondersgroup.datareport.dao;

import com.wondersgroup.datareport.model.TbCfgDatabase;
import com.wondersgroup.datareport.model.TbCfgDatabaseReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Date;
import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface DatabaseReportRepository extends JpaRepository<TbCfgDatabaseReport,Long> {


    @Query(value="select a.table_name,nvl(b.comments,'-'),nvl(a.num_rows,0) from ALL_tables a left join user_tab_comments b on a.table_name=b.table_name where a.owner=?1 ",nativeQuery = true)
    List<Object[]> getTableContent(String schemaName);

    List<TbCfgDatabaseReport> findByTableNameAndRemovedOrderByCreateDateDesc(String tableName,String removed);

    @Query(value="select * from tb_cfg_database_report r where r.database_id=?1 and create_date=?2",nativeQuery = true)
    List<TbCfgDatabaseReport> findAllByDatabaseAndMaxCreateData(Long id,Date maxDate);
}
