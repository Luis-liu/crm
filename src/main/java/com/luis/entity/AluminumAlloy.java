package com.luis.entity;

import com.luis.annotation.SqlField;
import com.luis.util.DoubleUtil;
import javafx.beans.property.*;

/**
 * 铝合金
 * Author:   liuyuansheng
 * Date:     2019/2/17 16:29
 */
public class AluminumAlloy {

    private IntegerProperty id = new SimpleIntegerProperty();
    private DoubleProperty height = new SimpleDoubleProperty();
    private DoubleProperty width = new SimpleDoubleProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty userId = new SimpleIntegerProperty();
    private StringProperty material = new SimpleStringProperty();

    private DoubleProperty area = new SimpleDoubleProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();

    public AluminumAlloy() {

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @SqlField("id")
    public void setId(int id) {
        this.id.set(id);
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    @SqlField("height")
    public void setHeight(double height) {
        this.height.set(height);
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    @SqlField("width")
    public void setWidth(double width) {
        this.width.set(width);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    @SqlField("price")
    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getArea() {
        return area.get();
    }

    public DoubleProperty areaProperty() {
        return area;
    }

    public void setArea(double area) {
        this.area.set(area);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    /**
     * 刷新面积和金额
     */
    public void refresh() {
        this.setArea(DoubleUtil.multiply(getWidth(), getHeight()));
        this.setAmount(DoubleUtil.multiply(getArea(), getPrice()));
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    @SqlField("user_id")
    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getMaterial() {
        return material.get();
    }

    public StringProperty materialProperty() {
        return material;
    }

    @SqlField("material")
    public void setMaterial(String material) {
        this.material.set(material);
    }

    @Override
    public String toString() {
        return "AluminumAlloy{" +
                "id=" + id.getValue() +
                ", userId=" + userId.getValue() +
                ", height=" + height.getValue() +
                ", width=" + width.getValue() +
                ", price=" + price.getValue() +
                ", area=" + area.getValue() +
                ", amount=" + amount.getValue() +
                ", material=" + material.getValue() +
                '}';
    }
}
