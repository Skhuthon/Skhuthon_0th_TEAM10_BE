package com.example.skhuthon_0th_team10.execption;

public class InvalidAuthorException extends RuntimeException{
    public InvalidAuthorException(String message) { super(message);}

    public InvalidAuthorException() {
        this("작성자 형식이 올바르지 않습니다.");
    }
}
