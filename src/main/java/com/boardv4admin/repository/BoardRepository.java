package com.boardv4admin.repository;

import com.boardv4admin.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    Optional<Board> findById(Long boardId);

    Board findByEngName(String engName);

    List<Board> findAll();
}
