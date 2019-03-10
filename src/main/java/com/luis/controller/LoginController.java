package com.luis.controller;

import com.luis.util.PropertiesUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-14 11:10
 **/
public class LoginController extends BaseController implements Initializable {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void login(ActionEvent event) {
        String password = passwordField.getText();
        String pd = PropertiesUtil.getProperties("password");
        if (pd.equals(password)) {
            logger.info("登陆成功");
            mainApp.showMainDialog();
        } else {
            logger.info("密码错误:{}", password);
            errorLabel.setVisible(true);
        }
    }
}
