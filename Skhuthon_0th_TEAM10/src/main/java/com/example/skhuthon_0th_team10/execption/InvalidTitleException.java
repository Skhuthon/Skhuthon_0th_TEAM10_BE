package com.example.skhuthon_0th_team10.execption;

public class InvalidTitleException extends RuntimeException{
    public InvalidTitleException(String message) {
        super(message);
    }

    public InvalidTitleException() {
        this("제목 형식이 올바르지 않습니다.");
    }
}
