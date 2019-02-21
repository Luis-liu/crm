package com.luis;

import com.luis.entity.SecurityNet;
import com.luis.service.SecurityNetService;
import org.junit.Test;

import java.util.List;

/**
 * Author:   liuyuansheng
 * Date:     2019/2/17 23:34
 */
public class SecurityNetServiceTest {

    private SecurityNetService service = new SecurityNetService();

    @Test
    public void addTest() {
        SecurityNet securityNet = new SecurityNet();
        securityNet.setUserId(1);
        securityNet.setPrice(1);
        securityNet.setHeight(1);
        securityNet.setWidth(1);
        securityNet.setPiao(1);
        int id = service.add(securityNet);
        System.out.println(id + "");
    }

    @Test
    public void queryTest() {
        List<SecurityNet> list = service.query(1);
        if (list != null) {
            for (SecurityNet securityNet : list) {
                System.out.println(securityNet);
            }
        }
    }

    @Test
    public void updateTest() {
        SecurityNet securityNet = new SecurityNet();
        securityNet.setId(1);
        securityNet.setPrice(20.05);
        securityNet.setHeight(28.05);
        securityNet.setWidth(18.05);
        securityNet.setPiao(10.22);
        service.update(securityNet);
        queryTest();
    }
}
