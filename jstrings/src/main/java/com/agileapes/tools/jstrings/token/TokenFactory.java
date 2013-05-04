package com.agileapes.tools.jstrings.token;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 2:57)
 */
public interface TokenFactory<T extends Token> {

    T getToken(String value, long position);

}
