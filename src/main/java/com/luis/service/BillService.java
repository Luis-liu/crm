package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.Bill;
import com.luis.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class BillService extends BaseService<Bill> {

    private static final Logger logger = LoggerFactory.getLogger(AlAlloyService.class);

    @Override
    public Integer add(Bill entity) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_BILL, entity.getUserId(),
                    entity.getPayTime(), entity.getAmount());
            id = CommonDao.addInfo(sql);
            logger.info("add Bill success");
        } catch (Exception e) {
            logger.error("error", e);
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
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public void update(Bill entity) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_BILL,
                    entity.getPayTime(), entity.getAmount(), entity.getId());
            CommonDao.addInfo(sql);
            logger.info("update Bill success");
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
