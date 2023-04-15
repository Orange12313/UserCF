package com.lch.domain.resbody;

import lombok.Data;

@Data
public class Result {
    private Boolean flag;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Result(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }
}

