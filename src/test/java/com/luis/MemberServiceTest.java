package com.luis;

import com.luis.constant.SqlConstant;
import com.luis.db.CommonDao;
import com.luis.entity.Member;
import com.luis.service.MemberService;
import org.junit.Test;

import java.util.List;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-16 11:07
 **/
public class MemberServiceTest {

    private MemberService memberService = new MemberService();

    @Test
    public void addMemberTest() {
        Member member = new Member("wanxioo", "18855444221");
        for (int i = 1; i < 3; i++) {
            member.setName("张三" + i);
            memberService.addMember(member);
        }
    }

    @Test
    public void queryMemberTest() {
        List<Member> list = memberService.queryMember("");
        if (list != null) {
            for (Member member : list) {
                System.out.println(member);
            }
        }
    }

    @Test
    public void updateMemberTest() {
        Member member = new Member();
        member.setUserId(1);
        member.setName("李四");
        member.setPhone("1558442547");
        memberService.updateMember(member);
        queryMemberTest();
    }
}
