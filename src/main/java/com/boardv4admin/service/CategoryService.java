package com.boardv4admin.service;

import com.boardv4admin.domain.Category;
import com.boardv4admin.exception.category.CategoryNotFoundException;
import com.boardv4admin.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //게시판 별 모든 카테고리 반환
    public List<Category> getAllCategoriesByBoard(Long boardId) {
        return categoryRepository.findByBoardId(boardId);
    }

    //ID에 해당하는 카테고리를 반환
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
