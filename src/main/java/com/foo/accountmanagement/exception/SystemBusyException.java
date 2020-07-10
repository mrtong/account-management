package com.foo.accountmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class SystemBusyException extends RuntimeException {
    public SystemBusyException(String message) {
        super(message);
    }
}
