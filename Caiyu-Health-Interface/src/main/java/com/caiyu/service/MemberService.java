package com.caiyu.service;

import com.caiyu.pojo.Member;

import java.util.List;

public interface MemberService {

    public void add(Member member);
    public Member findByTelephone(String telephone);
    public List<Integer> findMemberCountByMonth(List<String> month);
}
