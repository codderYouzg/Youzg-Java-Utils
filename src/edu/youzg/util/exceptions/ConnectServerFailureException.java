package edu.youzg.util.exceptions;

/**
 * 连接RMI服务器失败 异常
 */
public class ConnectServerFailureException extends Exception {
    private static final long serialVersionUID = -4943840549663693585L;

    public ConnectServerFailureException() {
    }

    public ConnectServerFailureException(String message) {
        super(message);
    }

    public ConnectServerFailureException(Throwable cause) {
        super(cause);
    }

    public ConnectServerFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectServerFailureException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

