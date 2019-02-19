package com.luis.constant;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-15 14:27
 **/
public class SqlConstant {

    /**
     * 新增客户
     */
    public static final String SQL_ADD_MEMBER = "insert into member(name, phone, create_date) values('%s','%s','%s')";

    /**
     * 更新客户
     */
    public static final String SQL_UPDATE_MEMBER = "update member set name='%s' , phone='%s' where user_id=%s";

    /**
     * 查询用户
     */
    public static final String SQL_QUERY_MEMBER = "select * from member";

    /**
     * 新增铝合金
     */
    public static final String SQL_ADD_ALALLOY = "insert into aluminum_alloy(user_id, height, width, price) values(%s, '%s','%s','%s')";

    /**
     * 更新铝合金
     */
    public static final String SQL_UPDATE_ALALLOY = "update aluminum_alloy set height='%s', width='%s', price='%s' where id=%s";

    /**
     * 查询铝合金
     */
    public static final String SQL_QUERY_ALALLOY = "select * from aluminum_alloy where user_id=%s";

    /**
     * 新增防盗网
     */
    public static final String SQL_ADD_SECURITYNET= "insert into security_net(user_id, height, width, price, piao) values(%s, '%s','%s','%s','%s')";

    /**
     * 更新防盗网
     */
    public static final String SQL_UPDATE_SECURITYNET = "update security_net set height='%s', width='%s', price='%s', piao='%s' where id=%s";

    /**
     * 查询防盗网
     */
    public static final String SQL_QUERY_SECURITYNET= "select * from security_net where user_id=%s";

    /**
     * 新增防盗网
     */
    public static final String SQL_ADD_OTHERMATERIAL= "insert into other_material(user_id, name, number, price) values(%s, '%s','%s','%s')";

    /**
     * 更新防盗网
     */
    public static final String SQL_UPDATE_OTHERMATERIAL = "update other_material set name='%s', number='%s', price='%s' where id=%s";

    /**
     * 查询防盗网
     */
    public static final String SQL_QUERY_OTHERMATERIAL= "select * from other_material where user_id=%s";
}
