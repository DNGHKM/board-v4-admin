package com.boardv4admin.repository;

import com.boardv4admin.domain.PostFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostFileRepository {
    void insertFiles(List<PostFile> postFiles);

    List<PostFile> findByPostId(Long postId);

    PostFile findBySavedFilename(String savedFilename);

    void deleteById(Long id);
}
