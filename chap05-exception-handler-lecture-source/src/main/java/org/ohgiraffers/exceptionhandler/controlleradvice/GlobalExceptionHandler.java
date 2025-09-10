package org.ohgiraffers.exceptionhandler.controlleradvice;

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
}
