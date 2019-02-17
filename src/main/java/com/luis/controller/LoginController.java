package com.luis.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void login(ActionEvent event) {
        String password = passwordField.getText();
        if ("1".equals(password)) {
            mainApp.showMainDialog();
        } else {
            errorLabel.setVisible(true);
        }
    }
}
