package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.SecurityNet;
import com.luis.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 防盗网
 * Author:   liuyuansheng
 * Date:     2019/2/19 21:42
 */
public class SecurityNetService implements CommonService<SecurityNet> {

    public static final Logger logger = LoggerFactory.getLogger(SecurityNetService.class);

    /**
     * 查询
     * @param userId
     * @return
     */
    @Override
    public List<SecurityNet> query(Object userId) {
        try {
            List<SecurityNet> resultList = new ArrayList<>();
            String sql = String.format(SqlConstant.SQL_QUERY_SECURITYNET, userId);
            List<Map<String, Object>> mapList = CommonDao.queryInfo(sql);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    SecurityNet securityNet = (SecurityNet)BeanUtil.fromMapToBean(SecurityNet.class, map);
                    securityNet.refresh();
                    resultList.add(securityNet);
                }
            }
            return resultList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 新增铝合金
     * @param securityNet
     */
    @Override
    public Integer add(SecurityNet securityNet) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_SECURITYNET, securityNet.getUserId(),
                    securityNet.getHeight(), securityNet.getWidth(), securityNet.getPrice(), securityNet.getPiao());
            id = CommonDao.addInfo(sql);
            logger.info("add securityNet success");
        } catch (Exception e) {
            logger.error("error", e);
        }
        return id;
    }

    /**
     * 更新铝合金
     * @param securityNet
     */
    @Override
    public void update(SecurityNet securityNet) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_SECURITYNET,
                    securityNet.getHeight(), securityNet.getWidth(), securityNet.getPrice(), securityNet.getPiao(), securityNet.getId());
            CommonDao.addInfo(sql);
            logger.info("update securityNet success");
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
