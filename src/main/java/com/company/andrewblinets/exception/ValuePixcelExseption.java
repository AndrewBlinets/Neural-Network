package com.company.andrewblinets.exception;

/**
 * Created by Андрей on 18.10.2017.
 */
public class ValuePixcelExseption extends Exception {
    public ValuePixcelExseption() {
    }

    public ValuePixcelExseption(String message) {
        super(message);
    }

    public ValuePixcelExseption(String message, Throwable cause) {
        super(message, cause);
    }

    public ValuePixcelExseption(Throwable cause) {
        super(cause);
    }

    public ValuePixcelExseption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
