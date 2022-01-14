package com.example.backenddevice.entity;


import lombok.Data;

/**
 * @author dyanjun
 * @date 2021/11/20 0:17
 */
@Data
public class Response {
    private final Integer code;

    private final String message;

    private final Object object;

    public Response(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", object=" + object +
                '}';
    }
}
