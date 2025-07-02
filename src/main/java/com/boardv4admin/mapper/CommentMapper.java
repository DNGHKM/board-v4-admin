package com.boardv4admin.mapper;

import com.boardv4admin.domain.Comment;
import com.boardv4admin.dto.comment.CommentResponse;
import com.boardv4admin.dto.comment.CommentWriteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "createAt", expression = "java(LocalDateTime.now())"),
    })
    Comment toEntity(Long memberId, CommentWriteRequest commentWriteRequest);

    CommentResponse toDTO(Comment comment, String name, String username);
}
