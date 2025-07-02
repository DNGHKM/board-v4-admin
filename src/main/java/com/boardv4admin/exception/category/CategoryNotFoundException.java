package com.boardv4admin.exception.category;

import com.boardv4admin.exception.base.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException() {
        super("해당 카테고리를 찾을 수 없습니다.");
    }
}
