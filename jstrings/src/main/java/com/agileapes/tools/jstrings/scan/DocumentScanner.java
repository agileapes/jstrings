package com.agileapes.tools.jstrings.scan;

import com.agileapes.tools.jstrings.error.MissingExpectedTokenException;
import com.agileapes.tools.jstrings.error.ScannerException;
import com.agileapes.tools.jstrings.error.ScannerReadException;
import com.agileapes.tools.jstrings.token.Token;

import java.util.regex.Pattern;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 8:29)
 */
public interface DocumentScanner<T extends Token> {

    /**
     * This will return the next character from the document without changing the
     * position of the cursor
     * @return the next character to be read
     */
    char peek() throws ScannerException;

    /**
     * This will return the next <em>n</em> characters from the document without changing the
     * position of the cursor
     * @param length    the number of characters to be read
     * @return the characters to come
     */
    String peek(int length) throws ScannerException;

    /**
     * This will read the next character in the document, moving forward by one
     * @return the next character
     */
    char read() throws ScannerException;

    /**
     * This will read the specified amount of characters from the document, moving forward
     * as reading, one position per character.
     * @param length    the number of characters to read
     * @return the read data
     */
    String read(int length) throws ScannerException;

    /**
     * Will read the string matching the specified pattern from the document, or do nothing.
     *
     * @param pattern    the pattern to match the text against
     * @return the read portion of the document, or the empty string ({@code ""}) if the next
     * characters do not match the given pattern
     */
    Token read(String pattern) throws ScannerException;

    /**
     * Will read the string matching the specified pattern from the document, or do nothing.
     *
     * @param pattern    the pattern to match the text against
     * @return the read portion of the document, or the empty string ({@code ""}) if the next
     * characters do not match the given pattern
     * @see #read(String)
     */
    Token read(Pattern pattern) throws ScannerException;

    /**
     * This method will attempt to read from the input document, until reaching one of the
     * specified delimiters, or the end of document
     *
     * @param delimiters    the delimiters signifying the end of required portion
     * @return the portion of the document that was read
     */
    Token readUntil(String... delimiters) throws ScannerException;

    /**
     * This will read the given character from the document, failing if it does not appear
     * @param characters    the characters expecting to appear
     * @return the read character
     * @throws MissingExpectedTokenException
     */
    char expect(char... characters) throws ScannerException;

    /**
     * This will attempt to read one of the given tokens from the input, failing if none exist
     *
     * @param tokens    the tokens to match against
     * @return the read token
     * @throws MissingExpectedTokenException
     */
    String expect(String ... tokens) throws MissingExpectedTokenException, ScannerReadException, ScannerException;

    /**
     * This will attempt to read a token from the document that matches one of the
     * given patterns. This method fails if no such token exists.
     *
     * @param patterns    the patterns to match againt
     * @return the read token
     * @throws MissingExpectedTokenException
     */
    Token expect(Pattern... patterns) throws ScannerException;

    /**
     * This method will check whether the next token in the document is the
     * token specified by the parameter.<br/>
     * <strong>NB</strong> the term token is used somewhat generally here, and does
     * not mean that this interface provides tokenization capabilities on its own.
     *
     * @param tokens    the string to match against
     * @return {@code true} if the remainder of the document starts with the
     * given token
     */
    String has(String... tokens) throws ScannerException;

    /**
     * This method will run the given parser over the remaining of the document
     * @param parser    the parser
     * @return an object model representing the parsed portion of the document
     */
    <E> E parse(SnippetParser<E> parser);

}
