package controller;

import entity.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import util.Message;
import util.StringUtil;

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
        if (StringUtil.isNotEmpty(name)) {
            if (member != null) {
                // 编辑客户
                if (!member.getName().equals(name)
                        && mainController.isMemberExist(name)) {
                    Message.showWarnMsg("客户姓名重复");
                    return;
                }
                member.setName(name);
                member.setPhone(phone);
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
            Message.showWarnMsg("客户姓名为空");
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }
}
