package com.boardv4admin.repository;

import com.boardv4admin.domain.Post;
import com.boardv4admin.dto.post.PostSearchCondition;
import com.boardv4admin.dto.post.PostSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostRepository {
    Optional<Post> findById(Long postId);

    void insert(Post post);

    void update(Post post);

    void softDelete(Long postId, String deletedSubject, String deletedContent);

    void hardDelete(Long postId);

    void increaseViewCount(Long postId);

    int countBySearch(PostSearchCondition dto);

    List<PostSummaryResponse> findBySearch(PostSearchCondition dto, int offset);

    List<PostSummaryResponse> findPinnedTop5(Long boardId);
}
