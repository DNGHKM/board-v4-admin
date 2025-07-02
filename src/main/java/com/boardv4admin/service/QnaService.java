package com.boardv4admin.service;

import com.boardv4admin.domain.Member;
import com.boardv4admin.domain.Qna;
import com.boardv4admin.dto.qna.*;
import com.boardv4admin.exception.qna.QnaNotFoundException;
import com.boardv4admin.mapper.QnaMapper;
import com.boardv4admin.repository.QnaRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QnaService {
    private final QnaRepository qnaRepository;
    private final MemberService memberService;
    private final QnaMapper qnaMapper;

    public QnaListResponse getQnaList(QnaSearchCondition request) {
        // 검색 결과 개수 조회
        int totalCount = qnaRepository.countBySearch(request);

        // 전체 페이지 수 계산
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / request.getSize()));

        //페이지 수 보정(1 미만 혹은 너무 큰 페이지 넘버가 들어오는 경우)
        request.adjustPage(totalPages);

        // 페이징 계산
        int offset = Math.min(
                (request.getPage() - 1) * request.getSize(),
                (totalCount / request.getSize()) * request.getSize()
        );

        // 게시글 목록 조회
        List<QnaSummaryResponse> qnaSummaryList = qnaRepository.findBySearch(request, offset);

        // 응답 DTO 구성
        return QnaListResponse.from(request, totalPages, totalCount, qnaSummaryList);
    }

    public Qna getQnaById(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id));
    }

    public QnaViewResponse getQnaViewById(Long qnaId) {
        Qna qna = getQnaById(qnaId);
        Member writer = memberService.getMemberById(qna.getWriterId());

        String answererName = null;
        if (qna.getAnswererId() != null) {
            answererName = memberService.getMemberById(qna.getAnswererId()).getName();
        }

        return qnaMapper.toViewDTO(qna, writer.getName(), answererName);
    }

    public void delete(Long qnaId) {
        Qna qna = getQnaById(qnaId);

        qnaRepository.delete(qna.getId());
    }


    public void writeAnswer(QnaAnswerRequest request, String username) {
        Qna qna = getQnaById(request.getQnaId());

        Member answerer = memberService.getAdminByUsername(username);
        qna.writeAnswer(request, answerer.getId());

        qnaRepository.updateAnswer(qna);
    }
}
