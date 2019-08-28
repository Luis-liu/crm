package com.luis.service.impl;

import com.luis.entity.AluminumAlloy;
import com.luis.entity.Bill;
import com.luis.entity.OtherMaterial;
import com.luis.entity.SecurityNet;
import com.luis.enums.TabNameEnum;
import com.luis.service.ExportDataSerive;
import javafx.beans.value.WritableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-08-26 16:56
 **/
public class ExportDataSeriveImpl implements ExportDataSerive {

    @Override
    public void exportExcelData(TabPane tabPane, File file) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (Tab tab : tabPane.getTabs()) {
            // 创建一个工作簿
            HSSFSheet sheet = workbook.createSheet(tab.getText());
            TableView tableView = (TableView)((Pane)tab.getContent()).getChildren().get(0);
            HSSFRow row = sheet.createRow(0);
            // 循环设置标题
            for (int i = 0; i < tableView.getColumns().size(); i++) {
                TableColumn tableColumn = (TableColumn)tableView.getColumns().get(i);
                String columnName = tableColumn.getText();
                row.createCell(i).setCellValue(columnName);
            }
            // 循环设值
            if (CollectionUtils.isNotEmpty(tableView.getItems())) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    Object object = tableView.getItems().get(i);
                    row = sheet.createRow(i + 1);
                    // 序号
                    row.createCell(0).setCellValue(i + 1);
                    outputItems(row, object);
                }
            }
        }
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    private void outputItems(HSSFRow row, Object item) {
        int i = 0;
        if (item instanceof AluminumAlloy) {
            if (item instanceof SecurityNet) {
                SecurityNet securityNet = (SecurityNet)item;
                row.createCell(++i).setCellValue(securityNet.getHeight());
                row.createCell(++i).setCellValue(securityNet.getWidth());
                row.createCell(++i).setCellValue(securityNet.getPiao());
                row.createCell(++i).setCellValue(securityNet.getArea());
                row.createCell(++i).setCellValue(securityNet.getPrice());
                row.createCell(++i).setCellValue(securityNet.getMaterial());
                row.createCell(++i).setCellValue(securityNet.getAmount());
            } else {
                AluminumAlloy aluminumAlloy = (AluminumAlloy)item;
                row.createCell(++i).setCellValue(aluminumAlloy.getHeight());
                row.createCell(++i).setCellValue(aluminumAlloy.getWidth());
                row.createCell(++i).setCellValue(aluminumAlloy.getArea());
                row.createCell(++i).setCellValue(aluminumAlloy.getPrice());
                row.createCell(++i).setCellValue(aluminumAlloy.getMaterial());
                row.createCell(++i).setCellValue(aluminumAlloy.getAmount());
            }
        } else if (item instanceof OtherMaterial) {
            OtherMaterial otherMaterial = (OtherMaterial)item;
            row.createCell(++i).setCellValue(otherMaterial.getName());
            row.createCell(++i).setCellValue(otherMaterial.getNumber());
            row.createCell(++i).setCellValue(otherMaterial.getPrice());
            row.createCell(++i).setCellValue(otherMaterial.getAmount());
        } else if (item instanceof Bill) {
            Bill bill = (Bill)item;
            row.createCell(++i).setCellValue(bill.getPayTime().toString());
            row.createCell(++i).setCellValue(bill.getAmount());
        }
    }

}
