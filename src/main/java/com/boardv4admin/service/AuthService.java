package com.boardv4admin.service;

import com.boardv4admin.domain.Member;
import com.boardv4admin.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final MemberService memberService;

    public void login(String username, String inputPassword, HttpSession session) {
        Member member = memberService.getAdminByUsername(username);

        PasswordUtil.validatePassword(inputPassword, member.getPassword());

        session.setAttribute("username", username);
        session.setMaxInactiveInterval(30 * 60); // 세션 30분
    }
}
