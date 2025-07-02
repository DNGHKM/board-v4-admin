package com.boardv4admin.domain;

import com.boardv4admin.dto.post.PostModifyRequest;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private Long memberId;
    private Long boardId;
    private Long categoryId;

    private String subject;
    private String content;
    private Integer viewCount;
    private LocalDateTime createAt;

    private boolean deleted;
    private boolean pinned;

    public void update(PostModifyRequest modifyDTO) {
        this.categoryId = modifyDTO.getCategoryId();
        this.subject = modifyDTO.getSubject();
        this.content = modifyDTO.getContent();
        this.pinned = modifyDTO.isPinned();
    }
}
