package org.ohgiraffers.handlerrmethod;

import lombok.*;

@NoArgsConstructor      // 스프링이 사용할 생성자
@AllArgsConstructor
@Getter
@Setter                 // 스프링이 사용 할 setter
@ToString

/* 설명. 사용자의 요청 파라미터를 담기위한 용도의 커맨드 객체용 클래스 */
public class MenuDTO {
    /* 설명. 사용자가 넘기는 parameter의 키 값과 일치하게 필드명 작성 */
    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;
}
