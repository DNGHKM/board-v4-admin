package com.boardv4admin.dto.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentWriteRequest {
    private Long boardId;
    private Long postId;
    private String content;
}
