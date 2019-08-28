package com.luis.service;

import javafx.scene.control.TabPane;

import java.io.File;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-08-26 15:52
 **/
public interface ExportDataSerive {
    /**
     * 导出数据到excel
     * @param tabPane
     * @param file
     * @throws Exception
     */
    void exportExcelData(TabPane tabPane, File file) throws Exception;
}
