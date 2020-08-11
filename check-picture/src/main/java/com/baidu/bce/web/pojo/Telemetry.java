package com.baidu.bce.web.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ContentRowHeight(140)
public class Telemetry implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("id")
    @ColumnWidth(6)
    private Long id;

    @ExcelProperty("体温值")
    @ColumnWidth(15)
    private Double temperature;


    @ExcelProperty("是否高温")
    @ColumnWidth(15)
    private Boolean alarm;

    @ExcelProperty("检测时间")
    @ColumnWidth(20)
    private Date inputTime;

    @ExcelProperty(value = "图片路径")
    @ColumnWidth(40)
    private String imagePath;

    @ExcelProperty(value = "高温图片")
    @ColumnWidth(40)
    private byte[] picture;

}