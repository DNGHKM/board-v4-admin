package com.boardv4admin.exception;

import com.boardv4admin.exception.base.FieldValidationException;
import com.boardv4admin.exception.base.ForbiddenException;
import com.boardv4admin.exception.base.NotFoundException;
import com.boardv4admin.exception.post.DeletedPostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException e, Model model) {
        model.addAttribute("msg", e.getMessage());
        return "error/alert";
    }

    @ExceptionHandler(DeletedPostException.class)
    public String handleDeletedPost(DeletedPostException e, Model model) {
        model.addAttribute("msg", e.getMessage());
        return "error/alert";
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbidden(ForbiddenException e, Model model) {
        model.addAttribute("msg", e.getMessage());
        return "error/alert";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {
        // 여러 필드 중 첫 번째 에러 메시지 선택
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("입력값이 잘못되었습니다.");

        model.addAttribute("msg", message);
        return "error/alert";
    }

    @ExceptionHandler(FieldValidationException.class)
    public String handleFieldValidation(FieldValidationException ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
        return "error/alert";
    }
}