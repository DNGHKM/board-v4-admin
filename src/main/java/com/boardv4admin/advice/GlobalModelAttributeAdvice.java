package com.boardv4admin.advice;

import com.boardv4admin.dto.board.BoardResponse;
import com.boardv4admin.service.BoardService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalModelAttributeAdvice {

    private final BoardService boardService;

    public GlobalModelAttributeAdvice(BoardService boardService) {
        this.boardService = boardService;
    }

    @ModelAttribute("boardList")
    public List<BoardResponse> populateBoardList() {
        return boardService.getBoardList(); // 전체 게시판 목록
    }
}
