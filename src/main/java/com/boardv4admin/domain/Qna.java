package com.boardv4admin.domain;

import com.boardv4admin.dto.qna.QnaAnswerRequest;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    private Long id;
    private Long writerId;
    private String subject;
    private String content;
    private LocalDateTime questionAt;
    private Long answererId;
    private String answer;
    private LocalDateTime answerAt;
    private boolean secret;
    private String password;
    private Integer viewCount;

    public boolean hasAnswer() {
        return !ObjectUtils.isEmpty(answer);
    }

    public void writeAnswer(QnaAnswerRequest request, Long answererId) {
        this.answererId = answererId;
        this.answer = request.getAnswer();
        this.answerAt = LocalDateTime.now();
    }
}
