package com.company.andrewblinets.exception;

/**
 * Created by Андрей on 25.10.2017.
 */
public class ResourseException extends Exception {
    public ResourseException() {
    }

    public ResourseException(String message) {
        super(message);
    }

    public ResourseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourseException(Throwable cause) {
        super(cause);
    }

    public ResourseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
