package com.sistema.blog.sistemablogspringbootapirest.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException {

    private HttpStatus status;
    private String msg;

    public BlogException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BlogException(HttpStatus status, String msg, String msg1) {
        this.status = status;
        this.msg = msg;
        this.msg = msg1;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
