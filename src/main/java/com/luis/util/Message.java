package com.luis.util;

import javafx.scene.control.Alert;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-14 17:49
 **/
public class Message {

    /**
     * 弹出提示信息
     * @param message
     */
    public static void showWarnMsg(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
