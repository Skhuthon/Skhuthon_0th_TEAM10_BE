package com.example.skhuthon_0th_team10.execption;

public class InvalidContentException extends RuntimeException{
    public InvalidContentException(String message) { super(message);}

    public InvalidContentException() {
        this("내용 형식이 올바르지 않습니다.");
    }
}
