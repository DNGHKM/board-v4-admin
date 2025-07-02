package com.boardv4admin.dto.post;

import com.boardv4admin.domain.Category;
import com.boardv4admin.domain.Post;
import com.boardv4admin.domain.PostFile;
import com.boardv4admin.dto.postFile.FileResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
public class PostViewResponse {

    private Long id;
    private Long boardId;
    private String subject;
    private String content;
    private String writer;
    private Long categoryId;
    private String categoryName;
    private int viewCount;
    private boolean pinned;
    private LocalDateTime createAt;
    private boolean deleted;

    private List<FileResponse> files;

    public static PostViewResponse from(Post post, String writer, Category category, List<PostFile> files) {
        return PostViewResponse.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .subject(post.getSubject())
                .content(post.getContent())
                .writer(writer)
                .categoryId(category.getId())
                .categoryName(category.getName())
                .viewCount(post.getViewCount())
                .pinned(post.isPinned())
                .createAt(post.getCreateAt())
                .deleted(post.isDeleted())
                .files(files.stream()
                        .map(f -> new FileResponse(f.getId(), f.getOriginalFilename(), f.getSavedFilename()))
                        .toList())
                .build();
    }
}
