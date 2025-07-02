package com.boardv4admin.dto.board;

import com.boardv4admin.enums.BoardType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponse {
    private Long boardId;
    private BoardType boardType;
    private String boardName;
}
