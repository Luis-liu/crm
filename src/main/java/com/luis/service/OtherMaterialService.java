package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.OtherMaterial;
import com.luis.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/19 23:10
 */
public class OtherMaterialService extends BaseService<OtherMaterial> {
    
    public static final Logger logger = LoggerFactory.getLogger(OtherMaterialService.class);

    @Override
    public Integer add(OtherMaterial entity) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_OTHERMATERIAL, entity.getUserId(),
                    entity.getName(), entity.getNumber(), entity.getPrice());
            id = CommonDao.addInfo(sql);
            logger.info("add OtherMaterial success");
        } catch (Exception e) {
            logger.error("error", e);
        }
        return id;
    }

    @Override
    public List<OtherMaterial> query(Object userId) {
        try {
            List<OtherMaterial> resultList = new ArrayList<>();
            String sql = format(SqlConstant.SQL_QUERY_OTHERMATERIAL, userId);
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
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public void update(OtherMaterial entity) {
        try {
            String sql = format(SqlConstant.SQL_UPDATE_OTHERMATERIAL,
                    entity.getName(), entity.getNumber(), entity.getPrice(), entity.getId());
            CommonDao.addInfo(sql);
            logger.info("update OtherMaterial success");
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
