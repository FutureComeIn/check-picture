package com.baidu.bce.web.mapper;

import com.baidu.bce.web.pojo.Telemetry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface TelemetryMapper {

    @Select("SELECT table_name FROM information_schema.tables WHERE table_name LIKE 'telemetry_2%'")
    public List<String> getTables();

    // TODO: tableName做校验，防sql注入
    // 查询出所有高温人员的数据
    @Select("select count(*) from $_parameter.tableName where alarm=1 and picture is not null and temperature>37.2")
    public Integer getHighTempQueryCount(@Param("tableName") String tableName);

    @Select("select id,temperature,alarm,input_time,picture from $_parameter.tableName where alarm=1 and picture is not null " +
            "and temperature>37.2 order by id desc")
    public List<Telemetry> getHighTempData(@Param("tableName") String tableName);

    // 查询出所有未高温人员的数据
    @Select("select count(*) from $_parameter.tableName where alarm=0 and picture is null")
    public Integer getLightTempQueryCount(@Param("tableName") String tableName);

    @Select("select id,temperature,alarm,input_time,picture from $_parameter.tableName where alarm=0 and picture is null order by id desc")
    public List<Telemetry> getLightTempData(@Param("tableName") String tableName);

}
