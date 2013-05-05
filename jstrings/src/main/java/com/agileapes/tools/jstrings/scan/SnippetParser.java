package com.agileapes.tools.jstrings.scan;

import com.agileapes.tools.jstrings.token.Token;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 9:00)
 */
public interface SnippetParser<E> {

    <T extends Token> E parse(DocumentScanner<T> scanner);

}
