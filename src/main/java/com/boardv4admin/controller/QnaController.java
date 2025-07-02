package com.boardv4admin.controller;

import com.boardv4admin.annotation.LoginUser;
import com.boardv4admin.dto.qna.QnaAnswerRequest;
import com.boardv4admin.dto.qna.QnaListResponse;
import com.boardv4admin.dto.qna.QnaSearchCondition;
import com.boardv4admin.dto.qna.QnaViewResponse;
import com.boardv4admin.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.boardv4admin.util.CommonUtil.redirectWithQueryString;

@Controller
@AllArgsConstructor
@RequestMapping("/qna")
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/list")
    public String qnaList(@ModelAttribute QnaSearchCondition searchCondition,
                          Model model,
                          HttpServletRequest request) {

        // 게시글 조회
        QnaListResponse response = qnaService.getQnaList(searchCondition);

        //페이징 보정 결과 반환
        searchCondition.setPage(response.getPage());

        model.addAttribute("queryString", searchCondition.getQueryString());
        model.addAttribute("qnaPageResult", response);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("requestURI", request.getRequestURI());

        return "list/qnaList";
    }

    @GetMapping("/modify/{qnaId}")
    public String modifyPage(@PathVariable Long qnaId, Model model) {
        QnaViewResponse qna = qnaService.getQnaViewById(qnaId);

        model.addAttribute("qna", qna);

        return "modify/qnaModify";
    }

    @PostMapping("/answer")
    public String writeAnswerQna(@ModelAttribute @Valid QnaAnswerRequest request,
                                 @RequestParam(required = false) String queryString,
                                 @LoginUser String username,
                                 RedirectAttributes redirectAttributes) {
        qnaService.writeAnswer(request, username);

        redirectAttributes.addFlashAttribute("msg", "답변을 등록하였습니다.");

        String redirectUrl = "redirect:/qna/modify/" + request.getQnaId();
        return redirectWithQueryString(redirectUrl, queryString);
    }

    @PostMapping("/{qnaId}/delete")
    public String deleteQna(@PathVariable Long qnaId,
                            @RequestParam(required = false) String queryString,
                            RedirectAttributes redirectAttributes) {
        qnaService.delete(qnaId);

        redirectAttributes.addFlashAttribute("msg", "문의 게시글을 삭제하였습니다.");

        String redirectUrl = "redirect:/qna/list";
        return redirectWithQueryString(redirectUrl, queryString);
    }
}
