package com.boardv4admin.exception.member;

import com.boardv4admin.exception.base.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException() {
        super("유저를 찾을 수 없습니다.");
    }
}
