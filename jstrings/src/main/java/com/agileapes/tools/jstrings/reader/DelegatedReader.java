package com.agileapes.tools.jstrings.reader;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 9:16)
 */
public class DelegatedReader extends Reader {

    private Reader reader;

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return getReader().read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        getReader().close();
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

}
