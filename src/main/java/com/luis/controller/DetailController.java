package com.luis.controller;

import com.luis.entity.AluminumAlloy;
import com.luis.entity.Member;
import com.luis.service.AlAlloyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    // 铝合金
    @FXML
    private TableView<AluminumAlloy> alAlloyTable;
    @FXML
    private TableColumn<AluminumAlloy, Integer> lvIdCol;
    @FXML
    private TableColumn<AluminumAlloy, Double> lvHeightCol, lvWidthCol, lvAreaCol, lvPriceCol, lvAmountCol;
    private ObservableList<AluminumAlloy> alAlloyTableData = FXCollections.observableArrayList();
    private AlAlloyService alAlloyService = new AlAlloyService();
    // 防盗网
    // 其他
    // 支付金额

    public void setMember(Member member) {
        this.member = member;
        nameLabel.setText(member.getName());
        phoneLabel.setText(member.getPhone());
        // 初始化数据
        alAlloyTableData.addAll(alAlloyService.queryAluminum(member.getUserId()));
        alAlloyTable.setItems(alAlloyTableData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 铝合金
        lvIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lvHeightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        lvHeightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        lvHeightCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setHeight(t.getNewValue());
            aluminumAlloy.refresh();
        });
        lvWidthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        lvWidthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lvWidthCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setWidth(t.getNewValue());
            aluminumAlloy.refresh();
        });
        lvAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        lvPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        lvPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lvPriceCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setPrice(t.getNewValue());
            aluminumAlloy.refresh();
        });
        lvAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
    }
}
