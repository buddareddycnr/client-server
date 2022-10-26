package com.openmedical.server.exceptions;

public class UserSaveOrUpdateOperationFailedException extends RuntimeException {


    public UserSaveOrUpdateOperationFailedException() {
        super();
    }

    public UserSaveOrUpdateOperationFailedException(String message) {
        super(message);
    }

    public UserSaveOrUpdateOperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserSaveOrUpdateOperationFailedException(Throwable cause) {
        super(cause);
    }

    protected UserSaveOrUpdateOperationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
