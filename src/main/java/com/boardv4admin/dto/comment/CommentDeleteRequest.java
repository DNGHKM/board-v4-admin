package com.boardv4admin.dto.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDeleteRequest {
    private Long boardId;
    private Long postId;
}
