package com.openmedical.server.exceptions;

public class UserDeleteOperationFailedException extends RuntimeException {


    public UserDeleteOperationFailedException() {
        super();
    }

    public UserDeleteOperationFailedException(String message) {
        super(message);
    }

    public UserDeleteOperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDeleteOperationFailedException(Throwable cause) {
        super(cause);
    }

    protected UserDeleteOperationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
