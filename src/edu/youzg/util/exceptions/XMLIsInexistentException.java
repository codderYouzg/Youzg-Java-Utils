package edu.youzg.util.exceptions;

import java.io.Serializable;

/**
 * xml配置文件不存在 异常
 *
 * @author Youzg
 */
public class XMLIsInexistentException extends Exception {
    private static final long serialVersionUID = 5631612417525749262L;

    public XMLIsInexistentException() {
        super();
    }

    public XMLIsInexistentException(String message) {
        super(message);
    }

    public XMLIsInexistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLIsInexistentException(Throwable cause) {
        super(cause);
    }

}
