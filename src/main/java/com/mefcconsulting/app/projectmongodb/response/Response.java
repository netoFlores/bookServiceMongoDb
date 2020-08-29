package com.mefcconsulting.app.projectmongodb.response;

import lombok.Data;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Response<T> {
    private String errorMessage;
    private Integer code;
    private T body;
}
