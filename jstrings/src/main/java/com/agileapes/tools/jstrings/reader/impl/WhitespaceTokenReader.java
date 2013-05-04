package com.agileapes.tools.jstrings.reader.impl;

import com.agileapes.tools.jstrings.token.Token;
import com.agileapes.tools.jstrings.token.TokenFactory;

import java.util.regex.Pattern;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 3:40)
 */
public class WhitespaceTokenReader<T extends Token> extends RegExpTokenReader<T> {

    public WhitespaceTokenReader(TokenFactory<T> tokenFactory) {
        super(tokenFactory, Pattern.compile("\\s+"));
    }

}
