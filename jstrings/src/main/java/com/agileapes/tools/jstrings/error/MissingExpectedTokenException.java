package com.agileapes.tools.jstrings.error;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 8:33)
 */
public class MissingExpectedTokenException extends ScannerException {

    private final String token;

    public MissingExpectedTokenException(String token) {
        super("Missing expected token: " + token);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
