package com.luis.controller;

import com.luis.entity.Member;
import com.luis.service.MemberService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.luis.util.Message;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController implements Initializable {

    @FXML
    private TableView<Member> myTable;
    @FXML
    private TableColumn<Member, Integer> userIdCol;
    @FXML
    private TableColumn<Member, String> createDateCol;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> phoneCol;
    @FXML
    private TableColumn<Member, String> operateCol;
    /**
     * 搜索字段
     */
    @FXML
    private TextField searchTextField;

    private MemberService memberService = new MemberService();

    private ObservableList<Member> tableData = FXCollections.observableArrayList();

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userIdCol.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
        createDateCol.setCellValueFactory(cellData -> cellData.getValue().createDateProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        // 初始化数据，查询所有客户
        tableData.addAll(memberService.query(null));

        myTable.setItems(tableData);

        operateCol.setCellFactory((col) -> {
            TableCell<Member, String> cell = new TableCell<Member, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Button detailBtn = new Button("账单");
                        detailBtn.setPrefWidth(90.0);
                        this.setGraphic(detailBtn);
                        detailBtn.setOnMouseClicked((me) -> {
                            Member member = this.getTableView().getItems().get(this.getIndex());
                            mainApp.showDetailDialog(member);
                        });
                    }
                }

            };
            return cell;
        });

        searchTextField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                searchMember();
            }
        });
    }


    /**
     * 搜索用户
     */
    public void searchMember() {
        String searchValue = searchTextField.getText();
        if (StringUtils.isNotEmpty(searchValue)) {
            tableData.setAll(memberService.query(searchValue));
        } else {
            tableData.setAll(memberService.query(null));
        }
    }

    /**
     * 搜索重置
     */
    public void resetSearch() {
        searchTextField.setText(null);
        searchMember();
    }

    /**
     * 弹出新增用户窗口
     * @param member
     */
    public void showCreateMemberDialog(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AddMemberScene.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            MemberAddController memberAddController = loader.getController();
            memberAddController.setDialogStage(dialogStage);
            memberAddController.setMember(member);
            memberAddController.setMainApp(mainApp);
            memberAddController.setMainController(this);

            dialogStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handleNewMember() {
        showCreateMemberDialog(null);
    }

    public void handleEditMember() {
        Member member = myTable.getSelectionModel().getSelectedItem();
        if (member != null) {
            showCreateMemberDialog(member);
        } else {
            Message.showWarnMsg("请选择一个客户进行编辑");
        }
    }

    public void addMember(Member member) {
        member.setUserId(tableData.size() + 1);
        tableData.addAll(member);
        memberService.add(member);
    }

    public void updateMember(Member member) {
        memberService.update(member);
    }

    /**
     * 判断会员重复
     * @param name
     * @return
     */
    public boolean isMemberExist(String name) {
        Member member = new Member();
        member.setName(name);
        return tableData.contains(member);
    }
}
