package com.boardv4admin.exception.board;

import com.boardv4admin.exception.base.NotFoundException;

public class BoardNotFoundException extends NotFoundException {
    public BoardNotFoundException() {
        super("해당 게시판을 찾을 수 없습니다.");
    }
}
