function withQueryString(baseUrl) {
    const query = location.search;
    return baseUrl + query;
}