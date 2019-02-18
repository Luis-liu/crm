package com.luis;

import com.luis.entity.AluminumAlloy;
import com.luis.service.AlAlloyService;
import org.junit.Test;

import java.util.List;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/17 23:34
 */
public class AlAlloyServiceTest {

    private AlAlloyService alAlloyService = new AlAlloyService();

    @Test
    public void addAluminumTest() {
        AluminumAlloy aluminumAlloy = new AluminumAlloy();
        aluminumAlloy.setUserId(1);
        aluminumAlloy.setPrice(10.05);
        aluminumAlloy.setHeight(8.05);
        aluminumAlloy.setWidth(8.05);
        alAlloyService.addAluminum(aluminumAlloy);
    }

    @Test
    public void queryAluminumTest() {
        List<AluminumAlloy> list = alAlloyService.queryAluminum(1);
        if (list != null) {
            for (AluminumAlloy aluminumAlloy : list) {
                System.out.println(aluminumAlloy);
            }
        }
    }

    @Test
    public void updateAluminumTest() {
        AluminumAlloy aluminumAlloy = new AluminumAlloy();
        aluminumAlloy.setId(1);
        aluminumAlloy.setPrice(20.05);
        aluminumAlloy.setHeight(28.05);
        aluminumAlloy.setWidth(18.05);
        alAlloyService.updateAluminum(aluminumAlloy);
    }
}
