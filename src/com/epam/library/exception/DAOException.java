package com.epam.library.exception;

/**
 * Created by Alexandra_Lavrenteva on 3/15/2017.
 */
public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
