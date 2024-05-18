package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.RequestMemberDTO;
import com.example.demo.exception.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    public Member createMember(RequestMemberDTO requestMemberDTO);
    public Member getMemberById(Long id) throws MemberNotFoundException;
    public List<Member> getMembers();
    public Member updateMemberById(Long id, RequestMemberDTO requestMemberDTO);
    public void deleteMemberById(Long id);
}
