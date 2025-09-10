package org.ohgiraffers.exceptionhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* 설명.
 *  우선순위: @ExceptionHandler 사용 > @ControllerAdvice > SimpleMappingExceptionResolver
 *
 * 설명.
 *  1. SimpleMappingExceptionResolver
 *   Spring 설정 기반으로 예외 타입 및 뷰 설정하는 전통적인 방식
 *  2. @ExceptionHandler
 *   특정 컨트롤러 내에서 발생하는 예외를 해당 컨트롤러의 메소드로 직접 처리하는 방식
 *  3. @ControllerAdvice 활용
 *   애플리케이션 전역에서 발생하는 예외를 중앙 집중식으로 처리하는 방식
* */

@SpringBootApplication
public class Chap05ExceptionHandlerLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap05ExceptionHandlerLectureSourceApplication.class, args);
    }

}
