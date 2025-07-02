//검색필터
function applySearchFilters() {
    const start = $('#startDate').val();
    const end = $('#endDate').val();

    if (!start || !end) {
        alert("시작일과 종료일을 모두 입력해 주세요.");
        return;
    }

    if (new Date(start) > new Date(end)) {
        alert("시작일은 종료일보다 이전이어야 합니다.");
        return;
    }

    const newParams = new URLSearchParams({
        page: 1,
        size: $('#size').val(),
        sortBy: $('#sortBy').val(),
        sortDirection: $('#sortDirection').val(),
    });

    if (start) newParams.set("startDate", start);
    if (end) newParams.set("endDate", end);
    if ($('#keyword').val()) newParams.set("keyword", $('#keyword').val());

    const queryString = `?${newParams.toString()}`;
    window.history.pushState({}, '', queryString);

    fetchAndRenderQna(Object.fromEntries(newParams.entries()));
}

//페이징
function renderPagination(currentPage, totalPages) {
    const $pagination = $('#pagination').empty();
    const baseParams = getSearchParams();
    const params = new URLSearchParams(baseParams);

    const groupSize = 10;
    const start = Math.floor((currentPage - 1) / groupSize) * groupSize + 1;
    const end = Math.min(start + groupSize - 1, totalPages);

    const createPageItem = (page, label = null, active = false) => {
        params.set("page", page);
        return `
                <li class="page-item ${active ? 'active' : ''}">
                    <a class="page-link" href="?${params.toString()}">${label || page}</a>
                </li>`;
    };

    if (currentPage > 1) $pagination.append(createPageItem(1, '«'));
    if (start > 1) $pagination.append(createPageItem(start - 1, '<'));

    for (let i = start; i <= end; i++) {
        $pagination.append(createPageItem(i, null, i === currentPage));
    }

    if (end < totalPages) $pagination.append(createPageItem(end + 1, '>'));
    if (currentPage < totalPages) $pagination.append(createPageItem(totalPages, '»'));
}

function setSearchFormValues(params) {
    $('#keyword').val(params.keyword);
    $('#startDate').val(params.startDate);
    $('#endDate').val(params.endDate);
    $('#size').val(params.size);
    $('#sortBy').val(params.sortBy);
    $('#sortDirection').val(params.sortDirection);
}


// 이벤트 바인딩
$('#searchForm').on('submit', function (e) {
    e.preventDefault();
    applySearchFilters();
});

$('#resetBtn').on('click', function (e) {
    e.preventDefault();
    location.href = `/qna/list`;
});

$('#size, #sortBy, #sortDirection').on('change', applySearchFilters);