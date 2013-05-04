package com.agileapes.tools.jstrings.reader.impl;

import com.agileapes.tools.jstrings.scan.impl.ReaderDocumentScanner;
import com.agileapes.tools.jstrings.token.Token;
import com.agileapes.tools.jstrings.token.impl.ValueToken;
import com.agileapes.tools.jstrings.token.impl.ValueTokenFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.StringReader;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 3:42)
 */
public class WhitespaceTokenReaderTest {

    @Test
    public void testRead() throws Exception {
        final ReaderDocumentScanner scanner = new ReaderDocumentScanner(new StringReader("   x"));
        final Token token = scanner.read(new WhitespaceTokenReader<ValueToken>(new ValueTokenFactory()));
        Assert.assertNotNull(token);
        Assert.assertEquals(token.getValue(), "   ");
        Assert.assertEquals(token.getPosition(), 0);
        Assert.assertTrue(scanner.hasMore());
        Assert.assertEquals(scanner.next(), 'x');
        Assert.assertFalse(scanner.hasMore());
    }
}
