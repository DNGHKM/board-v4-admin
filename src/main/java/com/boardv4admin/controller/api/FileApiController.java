package com.boardv4admin.controller.api;

import com.boardv4admin.service.PostFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/files")
@AllArgsConstructor
@Slf4j
public class FileApiController {
    private final PostFileService postFileService;

    @GetMapping("/{savedFilename}")
    public ResponseEntity<Resource> getPostView(@PathVariable String savedFilename) {
        return postFileService.download(savedFilename);
    }
}
