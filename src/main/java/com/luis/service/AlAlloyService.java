package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.AluminumAlloy;
import com.luis.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/17 21:40
 */
public class AlAlloyService implements CommonService<AluminumAlloy> {

    /**
     * 查询
     * @param userId
     * @return
     */
    @Override
    public List<AluminumAlloy> query(Object userId) {
        try {
            List<AluminumAlloy> resultList = new ArrayList<>();
            String sql = String.format(SqlConstant.SQL_QUERY_ALALLOY, userId);
            List<Map<String, Object>> mapList = CommonDao.queryInfo(sql);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    AluminumAlloy aluminumAlloy = (AluminumAlloy)BeanUtil.fromMapToBean(AluminumAlloy.class, map);
                    aluminumAlloy.refresh();
                    resultList.add(aluminumAlloy);
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增铝合金
     * @param aluminumAlloy
     */
    @Override
    public Integer add(AluminumAlloy aluminumAlloy) {
        int id = 0;
        try {
            String sql = String.format(SqlConstant.SQL_ADD_ALALLOY, aluminumAlloy.getUserId(),
                    aluminumAlloy.getHeight(), aluminumAlloy.getWidth(), aluminumAlloy.getPrice());
            id = CommonDao.addInfo(sql);
            System.out.println("add aluminumAlloy success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 更新铝合金
     * @param aluminumAlloy
     */
    @Override
    public void update(AluminumAlloy aluminumAlloy) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_ALALLOY,
                    aluminumAlloy.getHeight(), aluminumAlloy.getWidth(), aluminumAlloy.getPrice(), aluminumAlloy.getId());
            CommonDao.addInfo(sql);
            System.out.println("update aluminumAlloy success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
