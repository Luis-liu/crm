package com.luis.controller;

import com.luis.entity.*;
import com.luis.enums.TabNameEnum;
import com.luis.enums.TotalType;
import com.luis.service.*;
import com.luis.util.EditCell;
import com.luis.util.EntityUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * 铝合金
 * Author:   liuyuansheng
 * Date:     2019/2/17 13:42
 */
public class DetailController extends BaseController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(DetailController.class);

    private Member member;
    @FXML
    private Label nameLabel, phoneLabel, addressLabel;
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
    @FXML
    private TableColumn<AluminumAlloy, String> lvMaterialCol;
    private ObservableList<AluminumAlloy> alAlloyTableData = FXCollections.observableArrayList();
    private BaseService<AluminumAlloy> alAlloyService = new AlAlloyService();

    /**
     * 铝合金1
     */
    @FXML
    private TableView<AluminumAlloy> alAlloyTable1;
    @FXML
    private TableColumn<AluminumAlloy, Integer> lvIdCol1;
    @FXML
    private TableColumn<AluminumAlloy, Double> lvHeightCol1, lvWidthCol1, lvAreaCol1, lvPriceCol1, lvAmountCol1;
    @FXML
    private TableColumn<AluminumAlloy, String> lvMaterialCol1;
    private ObservableList<AluminumAlloy> alAlloyTableData1 = FXCollections.observableArrayList();

    /**
     * 防盗网
     */
    @FXML
    private TableView<SecurityNet> securityNetTable;
    @FXML
    private TableColumn<SecurityNet, Integer> snIdCol;
    @FXML
    private TableColumn<SecurityNet, Double> snHeightCol, snWidthCol, snAreaCol, snPriceCol, snAmountCol, snPiaoCol;
    @FXML
    private TableColumn<SecurityNet, String> snMaterialCol;
    private ObservableList<SecurityNet> securityNetTableData = FXCollections.observableArrayList();
    private BaseService<SecurityNet> securityNetService = new SecurityNetService();

    /**
     * 其他
     */
    @FXML
    private TableView<OtherMaterial> otherTable;
    @FXML
    private TableColumn<OtherMaterial, Integer> otherIdCol, otherNumberCol;
    @FXML
    private TableColumn<OtherMaterial, String> otherNameCol;
    @FXML
    private TableColumn<OtherMaterial, Double> otherPriceCol, otherAmountCol;
    private ObservableList<OtherMaterial> otherTableData = FXCollections.observableArrayList();
    private BaseService<OtherMaterial> otherMaterialService = new OtherMaterialService();
    /**
     * 支付金额
     */
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill, Integer> billIdCol;
    @FXML
    private TableColumn<Bill, LocalDate> billPayTimeCol;
    @FXML
    private TableColumn<Bill, Double> billAmountCol;
    private ObservableList<Bill> billTableData = FXCollections.observableArrayList();
    private BaseService<Bill> billService = new BillService();

    /**
     * 总数
     */
    @FXML
    private TableView<TotalData> totalTable;
    @FXML
    private TableColumn<TotalData, String> totalNameCol;
    @FXML
    private TableColumn<TotalData, Double> totalAreaCol, totalAmountCol;
    private ObservableList<TotalData> totalTableData = FXCollections.observableArrayList();
    private TotalData lvTotalData = new TotalData();
    private TotalData lvTotalData1 = new TotalData();
    private TotalData snTotalData = new TotalData();
    private TotalData otherTotalData = new TotalData();
    private TotalData billTotalData = new TotalData();


    /**
     * 设置会员对象，初始值
     * @param member
     */
    public void setMember(Member member) {
        this.member = member;
        nameLabel.setText(member.getName());
        phoneLabel.setText(member.getPhone());
        addressLabel.setText(member.getAddress());
        // 初始化铝合金数据
        alAlloyTableData.addAll(alAlloyService.query(member.getUserId()));
        alAlloyTable.setItems(alAlloyTableData);
        // 初始化铝合金1（其它1）数据
        alAlloyTableData1.addAll(alAlloyService.queryByEntity(new AluminumAlloy(member.getUserId(), 1)));
        alAlloyTable1.setItems(alAlloyTableData1);
        // 初始化防盗网数据
        securityNetTableData.addAll(securityNetService.query(member.getUserId()));
        securityNetTable.setItems(securityNetTableData);
        // 其他材料
        otherTableData.addAll(otherMaterialService.query(member.getUserId()));
        otherTable.setItems(otherTableData);
        // 付款金额
        billTableData.addAll(billService.query(member.getUserId()));
        billTable.setItems(billTableData);
        // 总数
        lvTotalData.setName(TabNameEnum.Al.getName());
        lvTotalData.setArea(EntityUtil.getAllData(alAlloyTableData, TotalType.AREA.getType()));
        lvTotalData.setAmount(EntityUtil.getAllData(alAlloyTableData, TotalType.AMOUNT.getType()));
        totalTableData.add(lvTotalData);

        lvTotalData1.setName(TabNameEnum.Al1.getName());
        lvTotalData1.setArea(EntityUtil.getAllData(alAlloyTableData1, TotalType.AREA.getType()));
        lvTotalData1.setAmount(EntityUtil.getAllData(alAlloyTableData1, TotalType.AMOUNT.getType()));
        totalTableData.add(lvTotalData1);

        snTotalData.setName(TabNameEnum.SN.getName());
        snTotalData.setArea(EntityUtil.getAllData(securityNetTableData, TotalType.AREA.getType()));
        snTotalData.setAmount(EntityUtil.getAllData(securityNetTableData, TotalType.AMOUNT.getType()));
        totalTableData.add(snTotalData);

        otherTotalData.setName(TabNameEnum.OM.getName());
        otherTotalData.setAmount(EntityUtil.getOtherTotalAmount(otherTableData));
        totalTableData.add(otherTotalData);

        billTotalData.setName(TabNameEnum.BL.getName());
        billTotalData.setAmount(EntityUtil.getBillTotalAmount(billTableData));
        totalTableData.add(billTotalData);

        totalTable.setItems(totalTableData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * 铝合金
         */
        lvIdCol.setCellFactory(col -> {
            TableCell<AluminumAlloy, Integer> cell = new TableCell<AluminumAlloy, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        lvHeightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        lvHeightCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        lvHeightCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setHeight(t.getNewValue());
            aluminumAlloy.refresh();
            lvTotalData.setArea(EntityUtil.getAllData(alAlloyTableData, TotalType.AREA.getType()));
            lvTotalData.setAmount(EntityUtil.getAllData(alAlloyTableData, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvWidthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        lvWidthCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        lvWidthCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setWidth(t.getNewValue());
            aluminumAlloy.refresh();
            // 刷新总平方
            lvTotalData.setArea(EntityUtil.getAllData(alAlloyTableData, TotalType.AREA.getType()));
            lvTotalData.setAmount(EntityUtil.getAllData(alAlloyTableData, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        lvPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        lvPriceCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        lvPriceCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setPrice(t.getNewValue());
            aluminumAlloy.refresh();
            // 刷新总金额
            lvTotalData.setAmount(EntityUtil.getAllData(alAlloyTableData, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        lvMaterialCol.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
        lvMaterialCol.setCellFactory(column -> EditCell.createStringEditCell());
        lvMaterialCol.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, String> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setMaterial(t.getNewValue());
            alAlloyService.update(aluminumAlloy);
        });

        /**
         * 铝合金1
         */
        lvIdCol1.setCellFactory(col -> {
            TableCell<AluminumAlloy, Integer> cell = new TableCell<AluminumAlloy, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        lvHeightCol1.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        lvHeightCol1.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        lvHeightCol1.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setHeight(t.getNewValue());
            aluminumAlloy.refresh();
            lvTotalData1.setArea(EntityUtil.getAllData(alAlloyTableData1, TotalType.AREA.getType()));
            lvTotalData1.setAmount(EntityUtil.getAllData(alAlloyTableData1, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvWidthCol1.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        lvWidthCol1.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        lvWidthCol1.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setWidth(t.getNewValue());
            aluminumAlloy.refresh();
            // 刷新总平方
            lvTotalData1.setArea(EntityUtil.getAllData(alAlloyTableData1, TotalType.AREA.getType()));
            lvTotalData1.setAmount(EntityUtil.getAllData(alAlloyTableData1, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvAreaCol1.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        lvPriceCol1.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        lvPriceCol1.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        lvPriceCol1.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, Double> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setPrice(t.getNewValue());
            aluminumAlloy.refresh();
            // 刷新总金额
            lvTotalData1.setAmount(EntityUtil.getAllData(alAlloyTableData1, TotalType.AMOUNT.getType()));
            alAlloyService.update(aluminumAlloy);
        });
        lvAmountCol1.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        lvMaterialCol1.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
        lvMaterialCol1.setCellFactory(column -> EditCell.createStringEditCell());
        lvMaterialCol1.setOnEditCommit((TableColumn.CellEditEvent<AluminumAlloy, String> t) -> {
            AluminumAlloy aluminumAlloy = t.getTableView().getItems().get(t.getTablePosition().getRow());
            aluminumAlloy.setMaterial(t.getNewValue());
            alAlloyService.update(aluminumAlloy);
        });

        /**
         * 防盗网
         */
        snIdCol.setCellFactory(col -> {
            TableCell<SecurityNet, Integer> cell = new TableCell<SecurityNet, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        snHeightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        // 设置为可编辑
        snHeightCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        // 编辑提交时触发的动作
        snHeightCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setHeight(t.getNewValue());
            securityNet.refresh();
            snTotalData.setArea(EntityUtil.getAllData(securityNetTableData, TotalType.AREA.getType()));
            snTotalData.setAmount(EntityUtil.getAllData(securityNetTableData, TotalType.AMOUNT.getType()));
            securityNetService.update(securityNet);
        });
        snWidthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        snWidthCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        snWidthCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setWidth(t.getNewValue());
            securityNet.refresh();
            snTotalData.setArea(EntityUtil.getAllData(securityNetTableData, TotalType.AREA.getType()));
            snTotalData.setAmount(EntityUtil.getAllData(securityNetTableData, TotalType.AMOUNT.getType()));
            securityNetService.update(securityNet);
        });
        snPiaoCol.setCellValueFactory(cellData -> cellData.getValue().piaoProperty().asObject());
        snPiaoCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        snPiaoCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setPiao(t.getNewValue());
            securityNet.refresh();
            snTotalData.setArea(EntityUtil.getAllData(securityNetTableData, TotalType.AREA.getType()));
            snTotalData.setAmount(EntityUtil.getAllData(securityNetTableData, TotalType.AMOUNT.getType()));
            securityNetService.update(securityNet);
        });
        snAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        snPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        snPriceCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        snPriceCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, Double> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setPrice(t.getNewValue());
            securityNet.refresh();
            snTotalData.setAmount(EntityUtil.getAllData(securityNetTableData, TotalType.AMOUNT.getType()));
            securityNetService.update(securityNet);
        });
        snAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        snMaterialCol.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
        snMaterialCol.setCellFactory(column -> EditCell.createStringEditCell());
        snMaterialCol.setOnEditCommit((TableColumn.CellEditEvent<SecurityNet, String> t) -> {
            SecurityNet securityNet = t.getTableView().getItems().get(t.getTablePosition().getRow());
            securityNet.setMaterial(t.getNewValue());
            securityNetService.update(securityNet);
        });
        /**
        /**
         * 其他
         */
        otherIdCol.setCellFactory(col -> {
            TableCell<OtherMaterial, Integer> cell = new TableCell<OtherMaterial, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        otherNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        otherNameCol.setCellFactory(column -> EditCell.createStringEditCell());
        otherNameCol.setOnEditCommit((TableColumn.CellEditEvent<OtherMaterial, String> t) -> {
            OtherMaterial otherMaterial = t.getTableView().getItems().get(t.getTablePosition().getRow());
            otherMaterial.setName(t.getNewValue());
            otherMaterialService.update(otherMaterial);
        });
        otherNumberCol.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        otherNumberCol.setCellFactory(column -> new EditCell<>(new IntegerStringConverter()));
        otherNumberCol.setOnEditCommit((TableColumn.CellEditEvent<OtherMaterial, Integer> t) -> {
            OtherMaterial otherMaterial = t.getTableView().getItems().get(t.getTablePosition().getRow());
            otherMaterial.setNumber(t.getNewValue());
            otherMaterial.refresh();
            otherTotalData.setAmount(EntityUtil.getOtherTotalAmount(otherTableData));
            otherMaterialService.update(otherMaterial);
        });
        otherPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        otherPriceCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        otherPriceCol.setOnEditCommit((TableColumn.CellEditEvent<OtherMaterial, Double> t) -> {
            OtherMaterial otherMaterial = t.getTableView().getItems().get(t.getTablePosition().getRow());
            otherMaterial.setPrice(t.getNewValue());
            otherMaterial.refresh();
            otherTotalData.setAmount(EntityUtil.getOtherTotalAmount(otherTableData));
            otherMaterialService.update(otherMaterial);
        });
        otherAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        /**
         * 付款金额
         */
        billIdCol.setCellFactory(col -> {
            TableCell<Bill, Integer> cell = new TableCell<Bill, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        billAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        billAmountCol.setCellFactory(column -> new EditCell<>(new DoubleStringConverter()));
        billAmountCol.setOnEditCommit((TableColumn.CellEditEvent<Bill, Double> t) -> {
            Bill bill = t.getTableView().getItems().get(t.getTablePosition().getRow());
            bill.setAmount(t.getNewValue());
            billTotalData.setAmount(EntityUtil.getBillTotalAmount(billTableData));
            billService.update(bill);
        });
        billPayTimeCol.setCellValueFactory(cellData -> cellData.getValue().payTimeProperty());
        billPayTimeCol.setCellFactory(column -> new EditCell<>(new LocalDateStringConverter()));
        billPayTimeCol.setOnEditCommit((TableColumn.CellEditEvent<Bill, LocalDate> t) -> {
            Bill bill = t.getTableView().getItems().get(t.getTablePosition().getRow());
            bill.setPayTime(t.getNewValue());
            billService.update(bill);
        });
        /**
         * 总数
         */
        totalNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        totalAreaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        totalAmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
    }

    /**
     * 添加数据
     */
    public void addDetailInfo() {
        String name = detailTabPane.getSelectionModel().getSelectedItem().getId();
        logger.info("addDetailInfo tab={}", name);
        int id;
        switch (name) {
            case "tab1":
                AluminumAlloy aluminumAlloy = new AluminumAlloy();
                aluminumAlloy.setUserId(member.getUserId());
                id = alAlloyService.add(aluminumAlloy);
                aluminumAlloy.setId(id);
                alAlloyTableData.add(aluminumAlloy);
                break;
            case "tab2":
                SecurityNet securityNet = new SecurityNet();
                securityNet.setUserId(member.getUserId());
                id = securityNetService.add(securityNet);
                securityNet.setId(id);
                securityNetTableData.add(securityNet);
                break;
            case "tab3":
                OtherMaterial otherMaterial = new OtherMaterial();
                otherMaterial.setUserId(member.getUserId());
                id =  otherMaterialService.add(otherMaterial);
                otherMaterial.setId(id);
                otherTableData.add(otherMaterial);
                break;
            case "tab4":
                Bill bill = new Bill();
                bill.setUserId(member.getUserId());
                bill.setPayTime(LocalDate.now());
                id = billService.add(bill);
                bill.setId(id);
                billTableData.add(bill);
                break;
            case "tab5":
                AluminumAlloy aluminumAlloy1 = new AluminumAlloy();
                aluminumAlloy1.setUserId(member.getUserId());
                aluminumAlloy1.setType(1);
                id = alAlloyService.add(aluminumAlloy1);
                aluminumAlloy1.setId(id);
                alAlloyTableData1.add(aluminumAlloy1);
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
