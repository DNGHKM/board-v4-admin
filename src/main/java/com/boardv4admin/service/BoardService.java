package com.boardv4admin.service;

import com.boardv4admin.domain.Board;
import com.boardv4admin.dto.board.BoardResponse;
import com.boardv4admin.exception.board.BoardNotFoundException;
import com.boardv4admin.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }

    public List<BoardResponse> getBoardList() {
        return boardRepository.findAll().stream()
                .map(board -> BoardResponse.builder()
                        .boardId(board.getId())
                        .boardType(board.getBoardType())
                        .boardName(board.getKorName())
                        .build())
                .collect(Collectors.toList());
    }
}
