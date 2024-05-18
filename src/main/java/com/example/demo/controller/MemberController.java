package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.RequestMemberDTO;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody RequestMemberDTO requestMemberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(requestMemberDTO));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        try {
            Member member = memberService.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMemberId(@PathVariable Long id, @RequestBody RequestMemberDTO requestMemberDTO) {
        return ResponseEntity.ok().body(memberService.updateMemberById(id, requestMemberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }
}

