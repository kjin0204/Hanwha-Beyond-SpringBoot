package org.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public static void test() {
        System.out.println("TestService의 test 메소드 호출");
    }
}
