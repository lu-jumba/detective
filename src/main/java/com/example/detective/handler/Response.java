package com.example.detective.handler;

//import helpers.service.ServiceStatus;
import lombok.Getter;

@Getter
public class Response<T> {
    private final String message;

    private final int code;

    private final T items;

    public Response(T object, ServiceStatus status){
        this.message = status.getMessage();
        this.code = status.getCode();
        this.items = object;
    }

}