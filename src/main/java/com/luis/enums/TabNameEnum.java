package com.luis.enums;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-18 11:29
 **/
public enum TabNameEnum {
    /**
     * 铝合金
     */
    Al("铝合金"),
    SN("防盗网"),
    OM("其它"),
    BL("付款账单"),
    Al1("其它1"),
    ;
    private String name;

    TabNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
