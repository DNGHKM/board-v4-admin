package com.boardv4admin.dto.qna;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QnaViewResponse {
    private Long id;
    private String writerName;
    private String subject;
    private String content;
    private LocalDateTime questionAt;
    private String answererName;
    private String answer;
    private LocalDateTime answerAt;
    private Integer viewCount;
}
