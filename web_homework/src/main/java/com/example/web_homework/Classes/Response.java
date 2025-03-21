package com.example.web_homework.Classes;

public class Response<Object> {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response<Object> construct(String msg, int success, Object data){
        this.msg = msg;
        this.code = 1;
        this.data = data;
        return this;
    }
}
