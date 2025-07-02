package com.boardv4admin.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostListResponse {
    private int page;
    private int size;
    private int totalPages;
    private long postCount;
    private List<PostSummaryResponse> posts;
    private List<PostSummaryResponse> pinnedPosts;

    public static PostListResponse from(PostSearchCondition dto,
                                        int totalPages,
                                        long totalCount,
                                        List<PostSummaryResponse> posts,
                                        List<PostSummaryResponse> pinnedPosts) {
        return PostListResponse.builder()
                .page(dto.getPage())
                .size(dto.getSize())
                .totalPages(totalPages)
                .postCount(totalCount)
                .posts(posts)
                .pinnedPosts(pinnedPosts)
                .build();
    }
}