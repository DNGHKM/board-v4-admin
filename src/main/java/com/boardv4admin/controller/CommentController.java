package com.boardv4admin.controller;

import com.boardv4admin.annotation.LoginUser;
import com.boardv4admin.dto.comment.CommentDeleteRequest;
import com.boardv4admin.dto.comment.CommentWriteRequest;
import com.boardv4admin.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.boardv4admin.util.CommonUtil.redirectWithQueryString;

@Controller
@RequestMapping("/comment")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String writeComment(@ModelAttribute CommentWriteRequest request,
                               @RequestParam(required = false) String queryString,
                               @LoginUser String username,
                               RedirectAttributes redirectAttributes) {
        commentService.write(username, request);

        redirectAttributes.addFlashAttribute("msg", "댓글을 등록하였습니다.");

        String redirectUrl = "redirect:/board/" + request.getBoardId() + "/modify/" + request.getPostId();
        return redirectWithQueryString(redirectUrl, queryString);
    }

    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId,
                                @RequestParam(required = false) String queryString,
                                @ModelAttribute CommentDeleteRequest request,
                                RedirectAttributes redirectAttributes) {
        commentService.deleteById(commentId);

        redirectAttributes.addFlashAttribute("msg", "댓글을 삭제하였습니다.");

        String redirectUrl = "redirect:/board/" + request.getBoardId() + "/modify/" + request.getPostId();
        return redirectWithQueryString(redirectUrl, queryString);
    }
}
