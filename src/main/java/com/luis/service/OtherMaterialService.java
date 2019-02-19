package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.AluminumAlloy;
import com.luis.entity.OtherMaterial;
import com.luis.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/19 23:10
 */
public class OtherMaterialService implements CommonService<OtherMaterial> {

    @Override
    public void add(OtherMaterial entity) {
        try {
            String sql = String.format(SqlConstant.SQL_ADD_OTHERMATERIAL, entity.getUserId(),
                    entity.getName(), entity.getNumber(), entity.getPrice());
            CommonDao.addInfo(sql);
            System.out.println("add OtherMaterial success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OtherMaterial> query(Object userId) {
        try {
            List<OtherMaterial> resultList = new ArrayList<>();
            String sql = String.format(SqlConstant.SQL_QUERY_OTHERMATERIAL, userId);
            List<Map<String, Object>> mapList = CommonDao.queryInfo(sql);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    OtherMaterial otherMaterial = (OtherMaterial)BeanUtil.fromMapToBean(OtherMaterial.class, map);
                    otherMaterial.refresh();
                    resultList.add(otherMaterial);
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(OtherMaterial entity) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_OTHERMATERIAL,
                    entity.getName(), entity.getNumber(), entity.getPrice(), entity.getId());
            CommonDao.addInfo(sql);
            System.out.println("update OtherMaterial success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
