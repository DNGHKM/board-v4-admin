package com.boardv4admin.dto.post;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostWriteResponse {
    private Long boardId;
    private Long postId;

    public static PostWriteResponse from(Long boardId, Long postId) {
        return PostWriteResponse.builder()
                .boardId(boardId)
                .postId(postId)
                .build();
    }
}
