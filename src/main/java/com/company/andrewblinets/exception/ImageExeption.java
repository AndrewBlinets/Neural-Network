package com.company.andrewblinets.exception;

/**
 * Created by Андрей on 25.10.2017.
 */
public class ImageExeption extends Exception {
    public ImageExeption() {
    }

    public ImageExeption(String message) {
        super(message);
    }

    public ImageExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageExeption(Throwable cause) {
        super(cause);
    }

    public ImageExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
