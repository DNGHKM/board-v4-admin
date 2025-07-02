package com.boardv4admin.dto.postFile;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileResponse {
    private Long id;
    private String originalFilename;
    private String savedFilename;
}
