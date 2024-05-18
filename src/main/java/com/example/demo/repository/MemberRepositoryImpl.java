package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Long, Member> memberStore = new HashMap<>();
    private static long memberSequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++memberSequence);
        memberStore.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }

    @Override
    public Member update(Member member) {
        memberStore.put(member.getId(), member);
        return member;
    }

    @Override
    public void deleteById(Long id) {
        memberStore.remove(id);
    }
}
