package com.baidu.bce.web.service;

import com.baidu.bce.web.pojo.Telemetry;

import java.util.List;

public interface TelemetryService {

    public List<String> getTables();

    public List<Telemetry> getHighTemp(String table);

    public Boolean exportHighData();

}
