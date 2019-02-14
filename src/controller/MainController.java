package controller;

import entry.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.StringUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button myButton;
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
    /**
     * 搜索字段
     */
    @FXML
    private TextField myTextField;

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

    final ObservableList<Member> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    /**
     * 搜索用户
     * @param event
     */
    public void searchMember(ActionEvent event) {
        System.out.println("Button Clicked!");

        myTextField.setText("hellow");

    }

    /**
     * 弹出新增用户窗口
     * @param event
     */
    public void showCreateMemberDialog(ActionEvent event) {
        try {
            Parent target  = FXMLLoader.load(getClass().getResource("../xml/AddMemberScene.fxml"));
            Scene scene = new Scene(target); //创建场景；
            Stage stg=new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存用户信息
     * @param event
     */
    public void saveMemberInfo(ActionEvent event) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(phone)) {
            tableData.add(new Member(name, phone));
            myTable.setItems(tableData);
        }
    }
}
