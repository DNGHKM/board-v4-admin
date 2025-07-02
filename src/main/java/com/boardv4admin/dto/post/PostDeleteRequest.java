package com.boardv4admin.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteRequest {
    private Long boardId;
    private Long postId;
}
