package com.boardv4admin.repository;

import com.boardv4admin.domain.Qna;
import com.boardv4admin.dto.qna.QnaSearchCondition;
import com.boardv4admin.dto.qna.QnaSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QnaRepository {
    Optional<Qna> findById(Long qnaId);

    void delete(Long qnaId);

    int countBySearch(QnaSearchCondition dto);

    List<QnaSummaryResponse> findBySearch(QnaSearchCondition dto, int offset);

    void updateAnswer(Qna qna);
}
