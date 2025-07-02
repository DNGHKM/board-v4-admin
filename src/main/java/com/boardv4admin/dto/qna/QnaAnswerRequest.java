package com.boardv4admin.dto.qna;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaAnswerRequest {
    private Long qnaId;
    private String answer;
}
