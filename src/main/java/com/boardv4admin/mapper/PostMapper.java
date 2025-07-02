package com.boardv4admin.mapper;

import com.boardv4admin.domain.Post;
import com.boardv4admin.dto.post.PostWriteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface PostMapper {

    @Mappings({
            @Mapping(target = "viewCount", constant = "0"),
            @Mapping(target = "createAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "deleted", constant = "false"),
    })
    Post toEntity(Long memberId, PostWriteRequest dto);
}
