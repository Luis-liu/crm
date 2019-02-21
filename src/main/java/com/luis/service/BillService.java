package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.Bill;
import com.luis.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-21 17:45
 **/
public class BillService implements CommonService<Bill> {

    @Override
    public Integer add(Bill entity) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_BILL, entity.getUserId(),
                    entity.getPayTime(), entity.getAmount());
            id = CommonDao.addInfo(sql);
            System.out.println("add Bill success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Bill> query(Object userId) {
        try {
            List<Bill> resultList = new ArrayList<>();
            String sql = String.format(SqlConstant.SQL_QUERY_BILL, userId);
            List<Map<String, Object>> mapList = CommonDao.queryInfo(sql);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    Bill bill = (Bill)BeanUtil.fromMapToBean(Bill.class, map);
                    resultList.add(bill);
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Bill entity) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_BILL,
                    entity.getPayTime(), entity.getAmount(), entity.getId());
            CommonDao.addInfo(sql);
            System.out.println("update Bill success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
