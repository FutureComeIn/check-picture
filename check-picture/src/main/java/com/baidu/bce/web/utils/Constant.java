package com.baidu.bce.web.utils;

import javax.swing.filechooser.FileSystemView;

public class Constant {
    public static final String TELEMETRY = "telemetry_";

    public static final String MAC_EXCEL_PATH = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/HighDate.xlsx";
    public static final String Windows_EXCEL_PATH = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "\\HighDate.xlsx";

    public static final String MAC_IMAGE_PATH = "/Users/v_yuanxiwen/Downloads/ts/image";
    public static final String WINDOWS_IMAGE_PATH = "C:/image";

    public static final String MAC_WEB_PATH = "file:///Users/v_yuanxiwen/Downloads/ts/image/";
    public static final String WINDOWS_WEB_PATH = "file:///C:/image/";

}
