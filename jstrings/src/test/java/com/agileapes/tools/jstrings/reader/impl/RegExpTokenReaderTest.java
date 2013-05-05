package com.agileapes.tools.jstrings.reader.impl;

import com.agileapes.tools.jstrings.scan.impl.DefaultDocumentReader;
import com.agileapes.tools.jstrings.token.Token;
import com.agileapes.tools.jstrings.token.impl.ValueToken;
import com.agileapes.tools.jstrings.token.impl.ValueTokenFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.StringReader;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 3:25)
 */
public class RegExpTokenReaderTest {

    @Test
    public void testReadingPattern() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("123+4"));
        final RegExpTokenReader<ValueToken> reader = new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+");
        final Token token = scanner.read(reader);
        Assert.assertNotNull(token);
        final String value = token.getValue();
        Assert.assertEquals(value, "123");
    }

    @Test
    public void testNotFindingPattern() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("hello123+4"));
        final Token token = scanner.read(new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+"));
        Assert.assertNull(token);
    }


    @Test
    public void testFindWithBufferOverreach() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("12345"), 2);
        final Token token = scanner.read(new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+"));
        Assert.assertNotNull(token);
        Assert.assertEquals(token.getValue(), "12");
    }

    @Test
    public void testNotFindingWithBufferOverreach() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("hello"), 2);
        final Token token = scanner.read(new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+"));
        Assert.assertNull(token);
    }

    @Test
    public void testLaziness() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("123.45"));
        final Token token = scanner.read(new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+(\\.\\d+)?"));
        Assert.assertNotNull(token);
        Assert.assertEquals(token.getValue(), "123");
    }

    @Test
    public void testGreediness() throws Exception {
        final DefaultDocumentReader scanner = new DefaultDocumentReader(new StringReader("123.45"));
        final Token token = scanner.read(new RegExpTokenReader<ValueToken>(new ValueTokenFactory(), "\\d+(\\.\\d*)?"));
        Assert.assertNotNull(token);
        Assert.assertEquals(token.getValue(), "123.45");
    }

}
