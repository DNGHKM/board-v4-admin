package com.boardv4admin.controller;

import com.boardv4admin.annotation.LoginUser;
import com.boardv4admin.domain.Board;
import com.boardv4admin.domain.Category;
import com.boardv4admin.dto.comment.CommentResponse;
import com.boardv4admin.dto.post.*;
import com.boardv4admin.service.BoardService;
import com.boardv4admin.service.CategoryService;
import com.boardv4admin.service.CommentService;
import com.boardv4admin.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.boardv4admin.util.CommonUtil.redirectWithQueryString;


@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @GetMapping("/{boardId}/list")
    public String boardList(@PathVariable Long boardId,
                            @ModelAttribute PostSearchCondition searchCondition,
                            Model model,
                            HttpServletRequest request) {
        Board board = boardService.getBoardById(boardId);
        PostListResponse response = postService.getPostList(searchCondition);
        List<Category> categories = categoryService.getAllCategoriesByBoard(boardId);

        //페이징 보정 결과 반영
        searchCondition.setPage(response.getPage());

        model.addAttribute("queryString", searchCondition.getQueryString());
        model.addAttribute("board", board);
        model.addAttribute("categories", categories);
        model.addAttribute("postPageResult", response);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("requestURI", request.getRequestURI());

        return board.getBoardType().getListView();
    }

    @GetMapping("/{boardId}/write")
    public String writePage(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoardById(boardId);
        List<Category> categories = categoryService.getAllCategoriesByBoard(boardId);

        model.addAttribute("board", board);
        model.addAttribute("categories", categories);

        return board.getBoardType().getWriteView();
    }

    @PostMapping("/posts")
    public String writePost(@ModelAttribute @Valid PostWriteRequest writeDTO,
                            @RequestParam(required = false) String queryString,
                            @LoginUser String username,
                            RedirectAttributes redirectAttributes) {
        Long postId = postService.write(username, writeDTO);

        redirectAttributes.addFlashAttribute("msg", "게시글을 작성하였습니다.");

        String redirectUrl = "redirect:/board/" + writeDTO.getBoardId() + "/modify/" + postId;
        return redirectWithQueryString(redirectUrl, queryString);
    }

    @GetMapping("/{boardId}/modify/{postId}")
    public String modifyPage(@PathVariable Long boardId, @PathVariable Long postId, Model model) {
        Board board = boardService.getBoardById(boardId);
        List<Category> categories = categoryService.getAllCategoriesByBoard(boardId);
        PostViewResponse post = postService.getPostViewById(postId);
        List<CommentResponse> comments = commentService.getCommentsByPostId(postId);

        model.addAttribute("board", board);
        model.addAttribute("categories", categories);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return board.getBoardType().getModifyView();
    }

    @PostMapping("/modify")
    public String modifyPost(@ModelAttribute @Valid PostModifyRequest modifyDTO,
                             @RequestParam(required = false) String queryString,
                             RedirectAttributes redirectAttributes) {

        postService.modify(modifyDTO);
        redirectAttributes.addFlashAttribute("msg", "게시글을 수정하였습니다.");

        String redirectUrl = "redirect:/board/" + modifyDTO.getBoardId() + "/modify/" + modifyDTO.getPostId();
        return redirectWithQueryString(redirectUrl, queryString);
    }

    @PostMapping("/delete")
    public String deletePost(@ModelAttribute @Valid PostDeleteRequest deleteDTO,
                             @RequestParam(required = false) String queryString,
                             RedirectAttributes redirectAttributes) {

        postService.delete(deleteDTO.getPostId());
        redirectAttributes.addFlashAttribute("msg", "게시글을 삭제하였습니다.");

        String redirectUrl = "redirect:/board/" + deleteDTO.getBoardId() + "/list";
        return redirectWithQueryString(redirectUrl, queryString);
    }
}
