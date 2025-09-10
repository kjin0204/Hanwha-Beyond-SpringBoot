package org.ohgiraffers.exceptionhandler.controlleradvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public String handleGlobalException(GlobalException e, Model model){
        model.addAttribute("exceptionMessage","전역에서 처리된 예외: " + e.getMessage());
        return "error/default";
    }

    /* 설명. ArithmeticException은 RuntimeException을 상속받고 있어서 이 ExceptionHandler로 처리 가능 */
    @ExceptionHandler(RuntimeException.class)
    public String handleRunTimeException(GlobalException e, Model model){
        model.addAttribute("exceptionMessage","전역에서 처리된 런타임 예외: " + e.getMessage());
        return "error/default";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(GlobalException e, Model model){
        model.addAttribute("exceptionMessage","전역에서 처리된 인수 예외: " + e.getMessage());
        return "error/default";
    }

    // ★ 정의되지 않은 모든 예외 (catch-all)
    @ExceptionHandler(Exception.class)
    public String handleOtherExceptions(Exception e, HttpServletRequest req, Model model){
        System.out.println("Unhandled exception at " + req.getMethod() +" , " + req.getRequestURI());
        model.addAttribute("exceptionMessage", "알 수 없는 예외가 발생했습니다.");
        // (선택) 상태코드도 500으로 명시하고 싶다면:
        req.setAttribute("javax.servlet.error.status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/default"; // 한 페이지로 포워드 (뷰 이름)
        // 혹은 "forward:/error/unexpected"로 강제 forward 가능
    }
}
