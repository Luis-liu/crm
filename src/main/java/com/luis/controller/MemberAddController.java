package com.luis.controller;

import com.luis.entity.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.luis.util.Message;
import org.apache.commons.lang3.StringUtils;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-14 15:06
 **/
public class MemberAddController extends BaseController {

    private Stage dialogStage;

    private Member member;

    private MainController mainController;

    /**
     * 客户姓名
     */
    @FXML
    private TextField nameField;
    /**
     * 客户电话
     */
    @FXML
    private TextField phoneField;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMember(Member member) {
        this.member = member;
        if (member != null) {
            nameField.setText(member.getName());
            phoneField.setText(member.getPhone());
        }
    }

    /**
     * 保存用户信息
     * @param event
     */
    public void saveMemberInfo(ActionEvent event) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(phone)) {
            if (member != null) {
                // 编辑客户
                if (!member.getName().equals(name)
                        && mainController.isMemberExist(name)) {
                    Message.showWarnMsg("客户姓名重复");
                    return;
                }
                member.setName(name);
                member.setPhone(phone);
                mainController.updateMember(member);
            } else {
                // 新增客户
                if (mainController.isMemberExist(name)) {
                    Message.showWarnMsg("客户姓名重复");
                    return;
                }
                Member newMember = new Member(name, phone);
                mainController.addMember(newMember);
            }
            dialogStage.close();
        } else {
            Message.showWarnMsg("姓名或者电话为空");
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }
}
