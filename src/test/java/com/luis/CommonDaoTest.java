package com.luis;

import com.luis.db.CommonDao;
import org.junit.Test;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-16 14:06
 **/
public class CommonDaoTest {

    @Test
    public void getTable() {
        CommonDao.crateTable();
    }
}
