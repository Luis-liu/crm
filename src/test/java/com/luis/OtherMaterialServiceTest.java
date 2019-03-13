package com.luis;

import com.luis.entity.OtherMaterial;
import com.luis.service.BaseService;
import com.luis.service.OtherMaterialService;
import org.junit.Test;

import java.util.List;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/19 23:19
 */
public class OtherMaterialServiceTest {

    private BaseService<OtherMaterial> service = new OtherMaterialService();

    @Test
    public void addTest() {
        OtherMaterial entity = new OtherMaterial();
        entity.setId(1);
        entity.setName("手机");
        entity.setUserId(1);
        entity.setNumber(20);
        entity.setPrice(20);
        service.add(entity);
        queryTest();
    }

    @Test
    public void updateTest() {
        OtherMaterial entity = new OtherMaterial();
        entity.setId(1);
        entity.setName("笔记本");
        entity.setUserId(1);
        entity.setNumber(10);
        entity.setPrice(10);
        service.update(entity);
        queryTest();
    }

    @Test
    public void queryTest() {
        List<OtherMaterial> list = service.query(1);
        if (list != null) {
            for (OtherMaterial entity : list) {
                System.out.println(entity);
            }
        }
    }
}
