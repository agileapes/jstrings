package com.agileapes.tools.jstrings.reader;

import com.agileapes.tools.jstrings.error.ScannerReadException;
import com.agileapes.tools.jstrings.scan.DocumentReader;
import com.agileapes.tools.jstrings.token.Token;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 1:43)
 */
public interface TokenReader {

    Token read(DocumentReader scanner) throws ScannerReadException;

}
