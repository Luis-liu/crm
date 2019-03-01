package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.Member;
import com.luis.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-16 11:09
 **/
public class MemberService implements CommonService<Member> {

    public static final String QUERY_SQL = " where name like '%s' or phone like '%s'";

    /**
     * 新增客户
     * @param member
     */
    @Override
    public Integer add(Member member) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_MEMBER, member.getName(), member.getPhone(), member.getCreateDate());
            id = CommonDao.addInfo(sql);
            System.out.println("add member success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 模糊查询会员
     * @param param
     * @return
     */
    @Override
    public List<Member> query(Object param) {
        try {
            String searchValue = param == null ? null : param.toString();
            List<Member> resultList = new ArrayList<>();
            String sql = SqlConstant.SQL_QUERY_MEMBER;
            if (StringUtils.isNotEmpty(searchValue)) {
                sql = sql + String.format(QUERY_SQL, cancatLike(searchValue), cancatLike(searchValue));
            }
            List<Map<String, Object>> mapList = CommonDao.queryInfo(sql);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    resultList.add((Member)BeanUtil.fromMapToBean(Member.class, map));
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新客户信息
     * @param member
     */
    @Override
    public void update(Member member) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_MEMBER, member.getName(), member.getPhone(), member.getUserId());
            CommonDao.addInfo(sql);
            System.out.println("update member success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接变量
     * @param str
     * @return
     */
    private String cancatLike(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return "%" + str + "%";
    }

}
