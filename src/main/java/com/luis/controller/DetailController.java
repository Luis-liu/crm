package com.luis.controller;

import com.luis.entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 铝合金
 * Author:   liuyuansheng
 * Date:     2019/2/17 13:42
 */
public class DetailController extends BaseController implements Initializable {

    private Member member;
    @FXML
    private Label nameLabel, phoneLabel;

    public void setMember(Member member) {
        this.member = member;
        nameLabel.setText(member.getName());
        phoneLabel.setText(member.getPhone());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
