package com.ohgiraffers.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* 설명.
     *  ResopnseEntity란?
     *   HttpRequest를 응답하기 위해 httpEntity를 상속받아 HttpStatus, HttpHeaders, HttpBody를
     *   정의하여 사용하는 클래스이다.
     *   (Http는 단순한 데이터 전송이 아니라 상태 정보를 포함한 통신 프로토콜이기 때문에, 올바른 상태 코드와
     *   헤더를 제공해야 클라이언트가 적절히 반응할 수 있다.)
     * */

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new java.util.Date()));
    }

    /* 설명. 1. ResponseEntity의 매개변수 있는 생성자를 토해 ResponseEntity를 반환하는 핸들러 메소드 */
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 설명. 2. 빌더를 활용한 메소드 체이닝 방식으로 ResponseEntity 객체 만들기(요즘 유행) */
    /* 설명. 빌더패턴: 생성자/setter를 사용하지 않고 내가 원하는 속성을 수정해서 불변객체로 만드는 패턴(feat. 내부 클래스) */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList()).get(0);

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200,userNo + "회원 조회성공",responseMap));
    }

}
