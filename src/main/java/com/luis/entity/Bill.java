package com.luis.entity;

import com.luis.annotation.SqlField;
import com.luis.util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-18 11:34
 **/
public class Bill {
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty userId = new SimpleIntegerProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> payTime = new SimpleObjectProperty<>();

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

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    @SqlField("amount")
    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public LocalDate getPayTime() {
        return payTime.get();
    }

    public ObjectProperty payTimeProperty() {
        return payTime;
    }

    @SqlField("pay_time")
    public void setPayTime(LocalDate payTime) {
        this.payTime.set(payTime);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id.getValue() +
                ", userId=" + userId.getValue() +
                ", amount=" + amount.getValue() +
                ", payTime=" + payTime.getValue() +
                '}';
    }
}
