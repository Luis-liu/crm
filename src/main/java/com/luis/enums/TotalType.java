package com.luis.enums;

public enum TotalType {
    /**
     * 总平方
     */
    AREA(1),
    /**
     * 总金额
     */
    AMOUNT(2);
    private int type;
    private TotalType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
