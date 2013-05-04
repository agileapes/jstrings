package com.agileapes.tools.jstrings.token.impl;

import com.agileapes.tools.jstrings.token.Token;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 2:50)
 */
public class ValueToken implements Token {

    private final String value;
    private final long position;

    public ValueToken(final String value, final long position) {
        this.value = value;
        this.position = position;
    }

    @Override
    public long getPosition() {
        return position;
    }

    @Override
    public String getValue() {
        return value;
    }
}
