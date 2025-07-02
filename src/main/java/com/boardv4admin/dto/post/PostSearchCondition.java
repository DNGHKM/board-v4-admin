package com.boardv4admin.dto.post;

import com.boardv4admin.enums.PostSortBy;
import com.boardv4admin.enums.SortDirection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

import static com.boardv4admin.constant.ValidationConstant.*;

@Getter
@Setter
public class PostSearchCondition {

    @NotNull
    private Long boardId;

    @Min(1)
    private int page = 1;

    @Min(1)
    private int size = 10;

    private Long categoryId;

    @NotNull(message = START_DATE_NOT_NULL)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull(message = END_DATE_NOT_NULL)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private String keyword;

    @NotNull(message = SORT_BY_NOT_NULL)
    private PostSortBy sortBy = PostSortBy.CREATE_DATE;

    @NotNull(message = SORT_DIRECTION_NOT_NULL)
    private SortDirection sortDirection = SortDirection.DESC;

    public void adjustPage(int totalPages) {
        this.page = Math.max(1, Math.min(this.page, totalPages));
    }

    public String getQueryString() {
        return UriComponentsBuilder.fromPath("")
                .queryParam("size", this.size)
                .queryParam("startDate", this.startDate)
                .queryParam("endDate", this.endDate)
                .queryParam("keyword", this.keyword)
                .queryParam("sortBy", this.sortBy)
                .queryParam("sortDirection", this.sortDirection)
                .build()
                .toUriString();
    }
}
