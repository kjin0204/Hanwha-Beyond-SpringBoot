package org.ohgiraffers.exceptionhandler.controlleradvice;

public class GlobalException extends RuntimeException{
    public GlobalException(String message){
        super(message);
    }
}
