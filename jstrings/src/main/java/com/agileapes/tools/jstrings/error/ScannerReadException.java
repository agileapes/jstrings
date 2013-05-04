package com.agileapes.tools.jstrings.error;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 1:45)
 */
public class ScannerReadException extends ScannerException {

    public ScannerReadException() {
    }

    public ScannerReadException(String message) {
        super(message);
    }

    public ScannerReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScannerReadException(Throwable cause) {
        super(cause);
    }
}
