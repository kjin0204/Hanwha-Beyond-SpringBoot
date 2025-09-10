
package org.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class ExceptionHandlerController {
    @GetMapping("simple-null")
    public String simpleNullPointerExceptionTest() {
        throw new NullPointerException();
    }

    @GetMapping("simple-user")
    public String userExceptionTest() throws MemberRegistException {
        if (true) {
            throw new MemberRegistException("당신은 안되요!");
        }
        return "test";
    }


    @GetMapping("annotation-null")
    public String nullPointerExceptionHandlerTest(){
        String str = null;
        str.charAt(0); //고의로 nullPointerException 처리

        return "/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler() {
        System.out.println("이 Controller에서 NullPointer 예외 발생 시 여기 오는지 확인");

        return "error/default";
    }

    @GetMapping("annotation-user")
    public String userExceptionHandlerTest() throws MemberRegistException {
        throw new MemberRegistException("당신은 안된다니깐?");
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model){
        System.out.println("이 Controller에서 MemberRegist 예외 발생 시 여기 오는지 확인");

        /* 설명. @ExceptionHandler로 해당 컨트롤러별 예외처리를 하면 전역 설정이 사라짐, 필요하면 model에 재료 추가 */
        model.addAttribute("exceptionMessage","당신은 안된다니까?!");
        return "error/default";
    }

}
