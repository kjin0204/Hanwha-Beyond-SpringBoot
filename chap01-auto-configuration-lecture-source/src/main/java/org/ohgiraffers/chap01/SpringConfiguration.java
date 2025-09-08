package org.ohgiraffers.chap01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Value("${test.value}")
    public String testValue;

    @Value("${test.age}")
    public int testAge;

    //System의 환경 변수도 불러 올 수 있음
    @Value("${username}")
    public String userName;

    @Bean
    public Object propertyReadTest(){
        System.out.println("testValue = " + testValue);
        System.out.println("testAge = " + testAge);
        System.out.println("username = " + userName);
        return new Object();
    }
}
