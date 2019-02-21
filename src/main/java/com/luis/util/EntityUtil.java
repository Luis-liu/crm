package com.luis.util;

import com.luis.entity.AluminumAlloy;
import com.luis.entity.Bill;
import com.luis.entity.OtherMaterial;

import java.util.List;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/21 23:11
 */
public class EntityUtil {

    /**
     * 获取每个列表的总数
     * @param list
     * @param type
     * @return
     */
    public static Double getAllData(List<? extends AluminumAlloy> list, int type) {
        Double total = 0d;
        if (list != null && list.size() > 0) {
            for (AluminumAlloy alloy : list) {
                if (type == 1) {
                    total = DoubleUtil.add(total, alloy.getArea());
                } else if (type == 2) {
                    total = DoubleUtil.add(total, alloy.getAmount());
                }
            }
        }
        return total;
    }

    public static Double getOtherTotalAmount(List<OtherMaterial> list) {
        Double total = 0d;
        if (list != null && list.size() > 0) {
            for (OtherMaterial otherMaterial : list) {
                total = DoubleUtil.add(total, otherMaterial.getAmount());
            }
        }
        return total;
    }

    public static Double getBillTotalAmount(List<Bill> list) {
        Double total = 0d;
        if (list != null && list.size() > 0) {
            for (Bill bill : list) {
                total = DoubleUtil.add(total, bill.getAmount());
            }
        }
        return total;
    }
}
