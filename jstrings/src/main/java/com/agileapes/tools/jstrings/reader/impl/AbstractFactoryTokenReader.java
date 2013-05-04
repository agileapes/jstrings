package com.agileapes.tools.jstrings.reader.impl;

import com.agileapes.tools.jstrings.reader.TokenReader;
import com.agileapes.tools.jstrings.token.Token;
import com.agileapes.tools.jstrings.token.TokenFactory;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 2:58)
 */
public abstract class AbstractFactoryTokenReader<T extends Token> implements TokenReader {

    private final TokenFactory<T> tokenFactory;

    public AbstractFactoryTokenReader(TokenFactory<T> tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    protected TokenFactory<T> getTokenFactory() {
        return tokenFactory;
    }

}
