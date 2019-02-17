package com.luis.entity;

import com.luis.annotation.SqlField;
import javafx.beans.property.*;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/17 16:29
 */
public class AluminumAlloy {

    private IntegerProperty id = new SimpleIntegerProperty();
    private DoubleProperty height = new SimpleDoubleProperty();
    private DoubleProperty width = new SimpleDoubleProperty();
    private DoubleProperty price = new SimpleDoubleProperty();

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
}
