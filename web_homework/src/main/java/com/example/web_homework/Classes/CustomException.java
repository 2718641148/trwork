package com.example.web_homework.Classes;

public class CustomException extends RuntimeException {
    private int status;  // HTTP状态码

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}