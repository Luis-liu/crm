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
    public void addSecurityNetTest() {
        SecurityNet securityNet = new SecurityNet();
        securityNet.setUserId(1);
        securityNet.setPrice(10.05);
        securityNet.setHeight(8.05);
        securityNet.setWidth(8.05);
        securityNet.setPiao(4.00);
        service.add(securityNet);
    }

    @Test
    public void querySecurityNetTest() {
        List<SecurityNet> list = service.query(1);
        if (list != null) {
            for (SecurityNet securityNet : list) {
                System.out.println(securityNet);
            }
        }
    }

    @Test
    public void updateSecurityNetTest() {
        SecurityNet securityNet = new SecurityNet();
        securityNet.setId(1);
        securityNet.setPrice(20.05);
        securityNet.setHeight(28.05);
        securityNet.setWidth(18.05);
        securityNet.setPiao(10.22);
        service.update(securityNet);
        querySecurityNetTest();
    }
}
