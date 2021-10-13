package com.caiyu.service;

import com.caiyu.pojo.Member;

public interface MemberService {

    public void add(Member member);
    public Member findByTelephone(String telephone);
}
