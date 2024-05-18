package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);
    public Optional<Member> findById(Long id);
    public List<Member> findAll();
    public Member update(Member member);
    public void deleteById(Long id);
}
