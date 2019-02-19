package com.luis.entity;

import com.luis.annotation.SqlField;
import com.luis.util.DoubleUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * 防盗网
 * Author:   liuyuansheng
 * Date:     2019/2/17 16:29
 */
public class SecurityNet extends AluminumAlloy {

    private DoubleProperty piao = new SimpleDoubleProperty();

    public double getPiao() {
        return piao.get();
    }

    public DoubleProperty piaoProperty() {
        return piao;
    }

    @SqlField("piao")
    public void setPiao(double piao) {
        this.piao.set(piao);
    }

    /**
     * 刷新面积和金额
     */
    @Override
    public void refresh() {
        // 平方=(高+飘x2)x(宽+飘x2)
        this.setArea(DoubleUtil.multiply(DoubleUtil.add(getHeight(), DoubleUtil.multiply(getPiao(), DoubleUtil.NUMBER2)),
                DoubleUtil.multiply(DoubleUtil.add(getWidth(), DoubleUtil.multiply(getPiao(), DoubleUtil.NUMBER2)))));
        this.setAmount(DoubleUtil.multiply(getArea(), getPrice()));
    }

    @Override
    public String toString() {
        return "AluminumAlloy{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", height=" + getHeight() +
                ", width=" + getWidth() +
                ", piao=" + getPiao() +
                ", price=" + getPrice() +
                ", area=" + getArea() +
                ", amount=" + getAmount() +
                '}';
    }

}
