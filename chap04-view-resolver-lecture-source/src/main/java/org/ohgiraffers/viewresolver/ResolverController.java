package org.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {
    /* 설명.
     *  ViewResolver(뷰리졸버): 인터페이스를 구현한 thymeleafViewResolver가 현재 처리하게 된다.
     *  접두사(prefix): classspath://templates/
     *  접미사(suffix): .html
     *  핸들러 메소드가 반환하는 문자열은 뷰리졸버로 향하게 되고 'redirect:'가 들어간 문자열의 경우에는
     *  접두사와 접미사가 추가 되지 않는다.
     * */

    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");
        return "result";
    }

    //servlet과 동일하게 request가 새로 생성되어 값이 유지 되지 않음
    @GetMapping("string-redirect")
    public String stringRedirect(Model model) {
        /* 설명. redirect 되어 요청이 다시 왔을 때 응답되는 view의 재료로 담는 걸 시도(model은 값이 유지 되지 않음) */
        model.addAttribute("message1", "문자열로 뷰 이름 반환하며 리다이렉트");
        return "redirect:/";
    }


    /* 설명. 스프링부트에서 리다이렉트 시에 값이 전달되게 하고 싶다면 ReirectAttributes에 flashAttribute로 추가  */
    /* 설명. 추가로, 내부적으로는 HttpSession을 활용하기 때문에 flashAttribute의 키 값이 기존 session의 키 값과 중복되면 x */
    @GetMapping("string-redirect-attr")
    public String StringRedirectFlashAttr(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage1", "문자열로 뷰 이름 반환하며 리다이렉트");
        return "redirect:/";
    }

    /* 설명. forward시에는 model에 재료를, redirect 시에는 ReirectAttributes에 재료를 담는다. */
    @GetMapping("modelandview")
    public ModelAndView modelAndViewTest(ModelAndView mv){
        mv.addObject("forwardMessage","ModelAndView를 이용한 forward");
        mv.setViewName("result");

        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){
        /* 설명. url 경로 상에 queryString형태로 파라미터로 넘어 간다. */
        mv.addObject("message2", "ModelAndView를 이용한 redirect");
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttr(ModelAndView mv,
                                                      RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;

    }
}
