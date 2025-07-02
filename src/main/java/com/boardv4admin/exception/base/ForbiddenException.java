package com.boardv4admin.exception.base;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("권한이 없습니다.");
    }
}
