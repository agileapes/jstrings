package com.agileapes.tools.jstrings.token.impl;

import com.agileapes.tools.jstrings.token.TokenFactory;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 3:00)
 */
public class ValueTokenFactory implements TokenFactory<ValueToken> {
    
    @Override
    public ValueToken getToken(String value, long position) {
        return new ValueToken(value, position);
    }
    
}
