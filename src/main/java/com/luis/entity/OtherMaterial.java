package com.luis.entity;

import com.luis.annotation.SqlField;
import com.luis.util.DoubleUtil;
import javafx.beans.property.*;

/**
 * 其他材料
 * Author:   liuyuansheng
 * Date:     2019/2/17 16:31
 */
public class OtherMaterial {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty("");
    private IntegerProperty number = new SimpleIntegerProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty userId = new SimpleIntegerProperty();

    private DoubleProperty amount = new SimpleDoubleProperty();

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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    @SqlField("name")
    public void setName(String name) {
        this.name.set(name);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    @SqlField("number")
    public void setNumber(int number) {
        this.number.set(number);
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

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
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

    /**
     * 刷新金额
     */
    public void refresh() {
        this.setAmount(DoubleUtil.multiply((double)getNumber(), getPrice()));
    }

    @Override
    public String toString() {
        return "OtherMaterial{" +
                "id=" + id.getValue() +
                ", userId=" + userId.getValue() +
                ", name=" + name.getValue() +
                ", number=" + number.getValue() +
                ", price=" + price.getValue() +
                ", amount=" + amount.getValue() +
                '}';
    }
}
