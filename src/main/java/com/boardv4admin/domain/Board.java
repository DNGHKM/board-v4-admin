package com.boardv4admin.domain;

import com.boardv4admin.enums.BoardType;
import com.boardv4admin.enums.FileType;
import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long id;
    private BoardType boardType;
    private String engName;
    private String korName;
    private boolean allowComment;
    private boolean allowPinned;
    private boolean writeAdminOnly;
    private Integer newDay;
    private FileType fileType;
    private Integer fileMaxCount;
    private Long fileMaxSize;
}
