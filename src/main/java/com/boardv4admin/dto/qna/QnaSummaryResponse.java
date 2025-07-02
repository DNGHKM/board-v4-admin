package com.boardv4admin.dto.qna;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class QnaSummaryResponse {
    private Long id;
    private String subject;
    private String writerName;
    private boolean hasAnswer;
    private boolean secret;
    private int viewCount;
    private LocalDateTime questionAt;
}
