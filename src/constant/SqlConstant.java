package constant;

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
    public static final String SQL_ADD_MEMBER = "insert into member(name, phone, create_date) values(%s,%s,%s)";

    /**
     * 更新客户
     */
    public static final String SQL_UPDATE_MEMBER = "update member set name=%s and phone=%s where user_id=%s";
}
