package com.baidu.bce.web.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.baidu.bce.web.pojo.Telemetry;
import com.baidu.bce.web.service.TelemetryService;
import com.baidu.bce.web.utils.Constant;
import com.baidu.bce.web.mapper.TelemetryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {
    @Autowired
    TelemetryMapper telemetryMapper;

    @Override
    public List<String> getTables() {
        List<String> tables = telemetryMapper.getTables();
        Collections.reverse(tables);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        String before = sdf.format(calendar.getTime());
        List<String> tableList = tables.stream().map(table -> table.substring(table.length() - 8))
                .filter(table -> table.compareTo(before) > 0)
                .collect(Collectors.toList());
        return tableList;
    }

    @Override
    public List<Telemetry> getHighTemp(String tableName) {
        List<Telemetry> telemetryList = new ArrayList<>();
        if (!StringUtils.isEmpty(tableName) && !("--选择日期--").equals(tableName)) {
            telemetryList = telemetryMapper.getHighTempData(Constant.TELEMETRY + tableName);
        }
        //创建存储图片文件夹
        File file = new File(Constant.MAC_IMAGE_PATH);
        if (file.exists()) {
            log.info("image directory is exists");
        } else {
            file.mkdirs();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        for (Telemetry t : telemetryList) {
            try (   //java7之后，自动关闭资源机制写法
                    InputStream fin = new ByteArrayInputStream(t.getPicture());
                    FileOutputStream fos = new FileOutputStream(file + "/" + t.getId() + ".bmp");
            ) {
                byte[] txt_byte = new byte[1024];
                int len = 0;
                while ((len = fin.read(txt_byte)) != -1) {
                    fos.write(txt_byte, 0, len);
                }
            } catch (Exception e) {
                log.error("图片读写失败");
            }
            t.setImagePath("/Path/" + t.getId() + ".bmp");
            t.setTemperature(Double.valueOf(df.format(t.getTemperature())));
        }
        return telemetryList;
    }

    @Override
    public Boolean exportHighData() {
        boolean flag = true;
        List<String> tables = getTables();
        List<Telemetry> highData = new ArrayList<>();
        for (String table : tables) {
            if (telemetryMapper.getHighTempQueryCount(Constant.TELEMETRY + table) > 0) {
                List<Telemetry> telemetryList = getHighTemp(table);
                highData.addAll(telemetryList);
            }
        }
        for (Telemetry t : highData) {
            t.setImagePath(Constant.MAC_IMAGE_PATH + "/" + t.getId() + ".bmp");
        }
        try {
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();    // 内容策略
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);   //水平居中
            WriteFont contentWriteFont = new WriteFont();         // 字体策略
            contentWriteFont.setFontHeightInPoints((short) 15);   // 字体大小
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            EasyExcel.write(Constant.MAC_EXCEL_PATH, Telemetry.class).sheet("高温数据").doWrite(highData);
        } catch (Exception e) {
            log.error("数据导出失败");
            flag = false;
        }
        return flag;
    }

}
