package controller;

import entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Message;
import util.StringUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController implements Initializable {

    @FXML
    private Button searchButton;
    @FXML
    TableView<Member> myTable;
    @FXML
    private TableColumn<Member, Integer> userIdCol;
    @FXML
    private TableColumn<Member, String> createDateCol;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> phoneCol;
    /**
     * 搜索字段
     */
    @FXML
    private TextField searchTextField;

    private ObservableList<Member> tableData = FXCollections.observableArrayList();

    public MainController() {
        tableData.addAll(new Member(1, "tom1", "1883225444"));
        tableData.addAll(new Member(2, "tom2", "1883225444"));
        tableData.addAll(new Member(3, "tom3", "1883225444"));
        tableData.addAll(new Member(4, "tom4", "1883225444"));
        tableData.addAll(new Member(5, "tom5", "1883225444"));
        tableData.addAll(new Member(6, "tom6", "1883225444"));
        tableData.addAll(new Member(7, "tom7", "1883225444"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userIdCol.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
        createDateCol.setCellValueFactory(cellData -> cellData.getValue().createDateProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        myTable.setItems(tableData);
    }

    /**
     * 搜索用户
     * @param event
     */
    public void searchMember(ActionEvent event) {
        String searchValue = searchTextField.getText();
        if (StringUtil.isNotEmpty(searchValue) && tableData.size() > 0) {
            ObservableList<Member> searchList = FXCollections.observableArrayList();
            for (Member obj : tableData) {
                // 模糊匹配姓名和手机号
                if (obj.getName().contains(searchValue)
                        || (StringUtil.isNotEmpty(obj.getPhone()) && obj.getPhone().contains(searchValue))) {
                    searchList.addAll(obj);
                }
                myTable.setItems(searchList);
            }
        } else {
            myTable.setItems(tableData);
        }
    }

    /**
     * 弹出新增用户窗口
     * @param member
     */
    public void showCreateMemberDialog(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddMemberScene.fxml"));
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
