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


    @Query(value="select a.table_name,nvl(b.comments,'-'),nvl(a.num_rows,0) from ALL_tables a left join all_tab_comments b on a.table_name=b.table_name where a.owner=?1 ",nativeQuery = true)
    List<Object[]> getTableContent(String schemaName);

    List<TbCfgDatabaseReport> findByTableNameAndReportTypeAndRemovedOrderByCreateDateDesc(String tableName,String reportType,String removed);

    @Query(value="select * from tb_cfg_database_report r where r.database_id=?1 and create_date=?2",nativeQuery = true)
    List<TbCfgDatabaseReport> findAllByDatabaseAndMaxCreateData(Long id,Date maxDate);

    @Query(value="select DISTINCT create_date from tb_cfg_database_report r where r.database_id=?1 and report_type=?2 and removed=?3 order by create_date ASC ",nativeQuery = true)
    List<Date> findDistinctCreateDate(String id,String reportType,String removed);

    @Query(value="select DISTINCT table_name from tb_cfg_database_report r where r.database_id=?1 and report_type=?2 and removed=?3 order by table_name ASC ",nativeQuery = true)
    List<String> findDistinctTableName(String id,String reportType,String removed);

    @Query(value="select data_number from tb_cfg_database_report r where TABLE_NAME =?1 and create_date=?2 and r.database_id=?3 and report_type=?4 and removed=?5 order by table_name ASC ",nativeQuery = true)
    String findDataNumber(String tableName,Date createDate,String id,String reportType,String removed);

}
