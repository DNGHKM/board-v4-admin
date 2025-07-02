package com.boardv4admin.util;

public class CommonUtil {
    public static String redirectWithQueryString(String redirectUrl, String queryString) {
        if (queryString != null && !queryString.isBlank()) {
            redirectUrl += queryString;
        }

        return redirectUrl;
    }
}
