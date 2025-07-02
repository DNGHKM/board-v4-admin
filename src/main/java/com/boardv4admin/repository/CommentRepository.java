package com.boardv4admin.repository;

import com.boardv4admin.domain.Comment;
import com.boardv4admin.dto.comment.CommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentRepository {
    Optional<Comment> findById(Long commentId);

    void insert(Comment comment);

    void delete(Long commentId);

    List<CommentResponse> findResponseListByPostId(Long postId);
}
