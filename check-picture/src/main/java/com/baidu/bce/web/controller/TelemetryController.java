package com.baidu.bce.web.controller;

import com.baidu.bce.web.service.TelemetryService;
import com.baidu.bce.web.pojo.Telemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TelemetryController {

    @Autowired
    TelemetryService telemetryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTables() {
        return telemetryService.getTables();
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.POST)
    @ResponseBody
    public List<Telemetry> getImgByTable(String tableName) {
        return telemetryService.getHighTemp(tableName);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public boolean exportToExcel() {
        return telemetryService.exportHighData();
    }
}
