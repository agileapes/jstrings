package com.agileapes.tools.jstrings.scan.impl;

import com.agileapes.tools.jstrings.error.ScannerException;
import com.agileapes.tools.jstrings.error.ScannerReadException;
import com.agileapes.tools.jstrings.reader.TokenReader;
import com.agileapes.tools.jstrings.scan.DocumentScanner;
import com.agileapes.tools.jstrings.token.Token;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 1:49)
 */
public class ReaderDocumentScanner implements DocumentScanner {

    public static final int DEFAULT_BUFFER_CAPACITY = 50;
    private final Reader reader;
    private String buffer = "";
    private int bufferPosition = 0;
    private int bufferCapacity = DEFAULT_BUFFER_CAPACITY;
    private long cursor = 0;

    public ReaderDocumentScanner(Reader reader) {
        this(reader, DEFAULT_BUFFER_CAPACITY);
    }

    public ReaderDocumentScanner(Reader reader, int bufferCapacity) {
        this.reader = reader;
        this.bufferCapacity = bufferCapacity;
    }

    @Override
    public Token read(TokenReader reader) throws ScannerReadException {
        flush();
        final Token token = reader.read(this);
        flush();
        return token;
    }

    @Override
    public boolean hasMore() throws ScannerReadException {
        return bufferPosition < buffer.length() || fillBuffer() != 0;
    }

    @Override
    public char next() throws ScannerReadException {
        if (!hasMore()) {
            throw new ScannerReadException("No more characters to read from the input");
        }
        char result = buffer.charAt(bufferPosition ++);
        cursor ++;
        if (bufferPosition >= bufferCapacity) {
            buffer = buffer.substring(bufferPosition - bufferCapacity + 1);
            bufferPosition = bufferCapacity - 1;
        }
        return result;
    }

    @Override
    public void flush() {
        buffer = buffer.substring(bufferPosition);
        bufferPosition = 0;
    }

    @Override
    public void rewind(int count) throws ScannerException {
        if (count > bufferPosition) {
            throw new ScannerException(String.format("Buffer overflow. Buffer remembers %d characters from the past, while requesting to go back %d positions", bufferPosition, count));
        }
        cursor -= count;
        bufferPosition -= count;
    }

    private int fillBuffer() throws ScannerReadException {
        int input;
        int count = 0;
        try {
            while ((input = reader.read()) != -1 && buffer.length() < bufferCapacity) {
                buffer += (char) input;
                count ++;
            }
        } catch (IOException e) {
            throw new ScannerReadException("Failed to fill buffer", e);
        }
        return count;
    }

    @Override
    public long getCursor() {
        return cursor;
    }

    @Override
    public long getBufferCapacity() {
        return bufferCapacity;
    }

}
