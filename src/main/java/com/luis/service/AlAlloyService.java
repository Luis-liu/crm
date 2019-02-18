package com.luis.service;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.AluminumAlloy;
import com.luis.entity.Member;
import com.luis.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.luis.service.MemberService.QUERY_SQL;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/17 21:40
 */
public class AlAlloyService {

    /**
     * 查询
     * @param userId
     * @return
     */
    public List<AluminumAlloy> queryAluminum(Integer userId) {
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
    public void addAluminum(AluminumAlloy aluminumAlloy) {
        try {
            String sql = String.format(SqlConstant.SQL_ADD_ALALLOY, aluminumAlloy.getUserId(),
                    aluminumAlloy.getHeight(), aluminumAlloy.getWidth(), aluminumAlloy.getPrice());
            CommonDao.addInfo(sql);
            System.out.println("add aluminumAlloy success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增铝合金
     * @param aluminumAlloy
     */
    public void updateAluminum(AluminumAlloy aluminumAlloy) {
        try {
            String sql = String.format(SqlConstant.SQL_UPDATE_ALALLOY,
                    aluminumAlloy.getHeight(), aluminumAlloy.getWidth(), aluminumAlloy.getPrice(), aluminumAlloy.getId());
            CommonDao.addInfo(sql);
            System.out.println("updateAluminum success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
