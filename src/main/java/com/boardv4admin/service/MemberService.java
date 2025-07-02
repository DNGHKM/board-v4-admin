package com.boardv4admin.service;

import com.boardv4admin.domain.Member;
import com.boardv4admin.exception.base.FieldValidationException;
import com.boardv4admin.exception.member.MemberNotFoundException;
import com.boardv4admin.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getAdminByUsername(String username) {
        return memberRepository.findAdminByUsername(username)
                .orElseThrow(MemberNotFoundException::new);
    }
}
