package com.boardv4admin.validator;

import com.boardv4admin.domain.Board;
import com.boardv4admin.enums.FileType;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class BoardPolicyValidator {

    /**
     * 게시판 정책 검증 (작성자 권한, 고정 가능 여부, 파일 정책 등)
     */
    public void validate(boolean isPinned, List<MultipartFile> files, Board board) {
        validatePinPolicy(isPinned, board);
        validateFilePolicy(files, board);
    }

    private void validatePinPolicy(boolean isPinned, Board board) {
        if (isPinned && !board.isAllowPinned()) {
            throw new IllegalArgumentException("게시글 고정이 지원되지 않는 게시판입니다.");
        }
    }

    private void validateFilePolicy(List<MultipartFile> files, Board board) {
        if (!ObjectUtils.isEmpty(files)) {
            return;
        }

        FileType fileType = board.getFileType();

        if (ObjectUtils.isEmpty(files)) {
            return;
        }

        if (fileType == FileType.NONE) {
            throw new IllegalArgumentException("파일 첨부가 허용되지 않는 게시판입니다.");
        }

        for (MultipartFile file : files) {
            validateExtension(fileType, file.getOriginalFilename());
            validateSize(board, file);
        }

        if (files.size() > board.getFileMaxCount()) {
            throw new IllegalArgumentException("파일 개수를 초과했습니다.");
        }
    }

    private void validateExtension(FileType fileType, String filename) {
        int idx = filename.lastIndexOf('.');
        if (idx == -1 || idx == filename.length() - 1) {
            throw new IllegalArgumentException("확장자가 없는 파일입니다.");
        }

        String ext = filename.substring(idx + 1).toLowerCase();
        if (!fileType.getAllowedExtensions().contains(ext)) {
            throw new IllegalArgumentException("허용되지 않은 확장자입니다.");
        }
    }

    private void validateSize(Board board, MultipartFile file) {
        if (file.getSize() > board.getFileMaxSize()) {
            throw new IllegalArgumentException("파일 크기가 초과되었습니다.");
        }
    }
}
