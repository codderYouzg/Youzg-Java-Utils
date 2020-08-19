package edu.youzg.util.exceptions;

/**
 * 目标frame为null异常
 * @author Youzg
 */
public class FrameIsNullException extends Exception {
    private static final long serialVersionUID = -8032684276437012616L;

    public FrameIsNullException() {
        super();
    }

    public FrameIsNullException(String message) {
        super(message);
    }

    public FrameIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

}
