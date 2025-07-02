package com.boardv4admin.mapper;

import com.boardv4admin.domain.Qna;
import com.boardv4admin.dto.qna.QnaViewResponse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface QnaMapper {
    QnaViewResponse toViewDTO(Qna qna, String writerName, String answererName);
}
