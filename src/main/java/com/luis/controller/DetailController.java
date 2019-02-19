package com.luis.controller;

import com.luis.entity.AluminumAlloy;
import com.luis.entity.Member;
import com.luis.entity.SecurityNet;
import com.luis.service.AlAlloyService;
import com.luis.service.CommonService;
import com.luis.service.SecurityNetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

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
    @FXML
    private TabPane detailTabPane;
    /**
     * 铝合金
     */
    @FXML
    private TableView<AluminumAlloy> alAlloyTable;
    @FXML
    private TableColumn<AluminumAlloy, Integer> lvIdCol;
    @FXML
    private TableColumn<AluminumAlloy, Double> lvHeightCol, lvWidthCol, lvAreaCol, lvPriceCol, lvAmountCol;
    private ObservableList<AluminumAlloy> alAlloyTableData = FXCollections.observableArrayList();
    private CommonService<AluminumAlloy> alAlloyService = new AlAlloyService();

    /**
     * 防盗网
     */
    @FXML
    private TableView<SecurityNet> securityNetTable;
    @FXML
    private TableColumn<SecurityNet, Integer> snIdCol;
    @FXML
    private TableColumn<SecurityNet, Double> snHeightCol, snWidthCol, snAreaCol, snPriceCol, snAmountCol, snPiaoCol;
    private ObservableList<SecurityNet> securityNetTableData = FXCollections.observableArrayList();
    private SecurityNetService securityNetService = new SecurityNetService();
    // 其他
    // 支付金额

    public void setMember(Member member) {
        this.member = member;
        nameLabel.setText(member.getName());
        phoneLabel.setText(member.getPhone());
        // 初始化铝合金数据
        alAlloyTableData.addAll(alAlloyService.query(member.getUserId()));
        alAlloyTable.setItems(alAlloyTableData);
        // 初始化防盗网数据
        securityNetTableData.addAll(securityNetService.query(member.getUserId()));
        securityNetTable.setItems(securityNetTableData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * 铝合金
         */
        lvIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lvHeightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        lvHeightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        lvHeightCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setHeight(t.getNewValue());
            aluminumAlloy.refresh();
            alAlloyService.update(aluminumAlloy);
        });
        lvWidthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        lvWidthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lvWidthCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setWidth(t.getNewValue());
            aluminumAlloy.refresh();
            alAlloyService.update(aluminumAlloy);
        });
        lvAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        lvPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        lvPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lvPriceCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setPrice(t.getNewValue());
            aluminumAlloy.refresh();
            alAlloyService.update(aluminumAlloy);
        });
        lvAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        /**
         * 防盗网
         */
        snIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        snHeightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        snHeightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        snHeightCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setHeight(t.getNewValue());
            securityNet.refresh();
            securityNetService.update(securityNet);
        });
        snWidthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        snWidthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        snWidthCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setWidth(t.getNewValue());
            securityNet.refresh();
            securityNetService.update(securityNet);
        });
        snPiaoCol.setCellValueFactory(cellData -> cellData.getValue().piaoProperty().asObject());
        snPiaoCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        snPiaoCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setPiao(t.getNewValue());
            securityNet.refresh();
            securityNetService.update(securityNet);
        });
        snAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        snPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        snPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        snPriceCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setPrice(t.getNewValue());
            securityNet.refresh();
            securityNetService.update(securityNet);
        });
        snAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
    }

    /**
     * 添加数据
     */
    public void addDetailInfo() {
        String name = detailTabPane.getSelectionModel().getSelectedItem().getText();
        switch (name) {
            case "铝合金":
                AluminumAlloy aluminumAlloy = new AluminumAlloy();
                aluminumAlloy.setUserId(member.getUserId());
                alAlloyService.add(aluminumAlloy);
                alAlloyTableData.add(aluminumAlloy);
                break;
            case "防盗网":
                SecurityNet securityNet = new SecurityNet();
                securityNet.setUserId(member.getUserId());
                securityNetService.add(securityNet);
                securityNetTableData.add(securityNet);
                break;
            case "其他":
                alAlloyService.add(new AluminumAlloy());
                break;
            case "付款账单":
                alAlloyService.add(new AluminumAlloy());
                break;
            default:
                break;
        }
    }

    /**
     * 返回主界面
     */
    public void returnMainScene() {
        mainApp.showMainDialog();
    }
}
