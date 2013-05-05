package com.agileapes.tools.jstrings.reader.impl;

import com.agileapes.tools.jstrings.error.ScannerException;
import com.agileapes.tools.jstrings.error.ScannerReadException;
import com.agileapes.tools.jstrings.scan.DocumentReader;
import com.agileapes.tools.jstrings.token.Token;
import com.agileapes.tools.jstrings.token.TokenFactory;

import java.util.regex.Pattern;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 3:15)
 */
public class RegExpTokenReader<T extends Token> extends AbstractFactoryTokenReader<T> {

    private final Pattern pattern;

    public RegExpTokenReader(TokenFactory<T> tokenFactory, Pattern pattern) {
        super(tokenFactory);
        this.pattern = pattern;
    }

    public RegExpTokenReader(TokenFactory<T> tokenFactory, String pattern) {
        this(tokenFactory, Pattern.compile(pattern, Pattern.DOTALL));
    }

    @Override
    public Token read(DocumentReader scanner) throws ScannerReadException {
        String buffer = "";
        if (pattern.matcher("").matches()) {
            //we will not allow for a pattern that matches the empty string
            return null;
        }
        while (scanner.hasMore() && buffer.length() < scanner.getBufferCapacity()) {
            buffer += scanner.next();
            if (buffer.length() > 1 && !pattern.matcher(buffer).matches()) {
                try {
                    scanner.rewind(1);
                } catch (ScannerException e) {
                    throw new ScannerReadException("Could not put back the non-matching part: " + buffer.substring(buffer.length() - 1));
                }
                buffer = buffer.substring(0, buffer.length() - 1);
                if (!pattern.matcher(buffer).matches()) {
                    return null;
                }
                return getTokenFactory().getToken(buffer, scanner.getCursor() - buffer.length());
            }
        }
        if (pattern.matcher(buffer).matches()) {
            return getTokenFactory().getToken(buffer, scanner.getCursor() - buffer.length());
        }
        return null;
    }

}
