package com.mefcconsulting.app.projectmongodb.exception;

import org.springframework.core.NestedRuntimeException;

public class ServiceException extends NestedRuntimeException {
    public ServiceException(String msg) {
        super(String.format("Service exception %s", msg));
    }
}
