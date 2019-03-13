package com.luis;

import com.luis.entity.Bill;
import com.luis.service.BillService;
import com.luis.service.BaseService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-21 17:50
 **/
public class BillServiceTest {

    private BaseService<Bill> service = new BillService();

    @Test
    public void add() {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setPayTime(LocalDate.now());
        bill.setAmount(100.33);
        service.add(bill);
    }

    @Test
    public void query() {
        List<Bill> list = service.query(1);
        if (list != null) {
            for (Bill entity : list) {
                System.out.println(entity);
            }
        }
    }

    @Test
    public void update() {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setId(1);
        bill.setPayTime(LocalDate.of(2022, 9, 10));
        bill.setAmount(777.33);
        service.update(bill);
        query();
    }
}
