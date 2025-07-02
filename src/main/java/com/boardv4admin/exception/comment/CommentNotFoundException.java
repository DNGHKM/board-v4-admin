package com.boardv4admin.exception.comment;

import com.boardv4admin.exception.base.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(Long commentId) {
        super("해당 댓글을 찾을 수 없습니다. (id: " + commentId + ")");
    }
}