package com.ohgiraffers.restapi.section02.responseentity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "사용자 정보 DTO")
public class UserDTO {
    @Schema(description = "사용자 번호", example = "1")
    private int no;
    @Schema(description = "사용자 아이디", example = "user01")
    private String id;
    @Schema(description = "비밀번호", example = "pass01")
    private String pwd;
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
    @Schema(description = "가입일시", example = "2024-01-01T10:00:00.000+00:00")
    private java.util.Date enrollAt;

    public UserDTO() {
    }

    public UserDTO(int no, String id, String pwd, String name, Date enrollAt) {
        this.no = no;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.enrollAt = enrollAt;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEnrollAt() {
        return enrollAt;
    }

    public void setEnrollAt(Date enrollAt) {
        this.enrollAt = enrollAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", enrollAt=" + enrollAt +
                '}';
    }
}
