package com.boardv4admin.dto.qna;

import com.boardv4admin.domain.Qna;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QnaAnswerResponse {
    private Long id;
    private String answererName;
    private String answer;
    private LocalDateTime answerAt;

    public static QnaAnswerResponse from(Qna qna, String answererName) {
        return QnaAnswerResponse.builder()
                .id(qna.getId())
                .answererName(answererName)
                .answer(qna.getAnswer())
                .answerAt(qna.getAnswerAt()).build();
    }
}
