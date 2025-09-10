package org.ohgiraffers.exceptionhandler.controlleradvice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/global")
public class GlobalTestController {
    @GetMapping("/global-exception")
    public String globalExceptionTest(){
        System.out.println("GlobalException 발생 테스트");
        throw new GlobalException("전역 예외 처리 테스트");
    }

    @GetMapping("/runtime-exception")
    public String runtimeExceptionTest(){
        System.out.println("RunTimeException 발생 테스트");
        throw new RuntimeException("런타임 예외 발생 테스트");
    }

    @GetMapping("/illegal-exception")
    public String illegalArgumentExceptionTest(){
        System.out.println("IllegalArgumentException 발생 테스트");
        throw new IllegalStateException("잘못된 인수가 전달됨");
    }

    @GetMapping("/arithmetic-exception")
    public String artihmeitcExceptionTest(){
        System.out.println("artihmeitcException 발생 테스트");
        int result = 10 / 0;
        return "test";
    }
}

