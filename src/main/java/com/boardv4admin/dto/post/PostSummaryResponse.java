package com.boardv4admin.dto.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostSummaryResponse {
    private Long id;
    private String categoryName;
    private String subject;
    private String writerName;
    private Boolean pinned;
    private String thumbnailFilename;
    private Integer commentCount;
    private Integer fileCount;
    private int viewCount;
    private LocalDateTime createAt;
}
