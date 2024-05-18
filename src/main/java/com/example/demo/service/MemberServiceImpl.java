package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.RequestMemberDTO;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(RequestMemberDTO requestMemberDTO) {
        return memberRepository.save(
                new Member(
                        requestMemberDTO.getName(),
                        requestMemberDTO.getEmail(),
                        requestMemberDTO.getPassword()
                ));
    }

    @Override
    public Member getMemberById(Long id) throws MemberNotFoundException {
        return memberRepository.findById(id).orElseThrow(() ->
                new MemberNotFoundException("Member not found with id: " + id));
    }

    @Override
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    @Override
    public Member updateMemberById(Long id, RequestMemberDTO requestMemberDTO) {
        Member existMember = memberRepository.findById(id).orElseThrow(() ->
                new MemberNotFoundException("Member not found with id: " + id));

        Member updateMember = new Member(
                requestMemberDTO.getName(),
                requestMemberDTO.getEmail(),
                requestMemberDTO.getPassword()
        );

        updateMember.setId(existMember.getId());

        return memberRepository.update(updateMember);
    }

    @Override
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
