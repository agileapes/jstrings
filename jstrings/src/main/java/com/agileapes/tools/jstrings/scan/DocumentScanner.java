package com.agileapes.tools.jstrings.scan;

import com.agileapes.tools.jstrings.error.ScannerException;
import com.agileapes.tools.jstrings.error.ScannerReadException;
import com.agileapes.tools.jstrings.reader.TokenReader;
import com.agileapes.tools.jstrings.token.Token;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 1:39)
 *
 * The document scanner interface is a generic interface designed to ease the process of reading
 * through a document which is consisted of meaningful (or at least well-formed) strings.
 */
public interface DocumentScanner {

    /**
     * This method is a delegate that abstracts the more complex of the actions we might want
     * to take over a string at any given point in time. A flush will be issued immediately before
     * and  after a read operation, since a reader is supposed to encapsulate a complete read
     * operation that must not allow other operations to interfere with its work.
     * @param reader    the reader that will do the job
     * @return a token descriptor that has to have the token's actual value in it
     * @throws ScannerReadException in case any errors are thrown by the reader or if the reader
     * attempts to reach back further in the document than the place it initially started with.
     */
    Token read(TokenReader reader) throws ScannerReadException;

    /**
     * Will determine whether there are more characters to go forward with.
     * @return {@code true} if more characters exist
     * @throws ScannerReadException
     */
    boolean hasMore() throws ScannerReadException;

    /**
     * This will return the next character in the input. This might come from a cached buffer
     * of characters, or might result in an actual read operation from the resource assigned to
     * the scanner.
     * @return the next character in the input
     * @throws ScannerReadException if no more characters are ready for reading
     */
    char next() throws ScannerReadException;

    /**
     * This will flush the readers buffer (if applicable). It is always a good idea to flush
     * the buffer once we are sure that we would not want to go back to a place back in the
     * stream using {@link #rewind(int)}. This way, we will allow for the read-ahead buffer to
     * be filled periodically, resulting in collocated data to be loaded faster and in batches,
     * rather than individually and on a need-to-get basis.
     */
    void flush();

    /**
     * This method will attempt to go back the indicated number of characters into the stream
     * if that is possible and reposition the cursor.
     * @param count    the number of characters to go back
     * @throws ScannerException if we attempt to overreach the buffer. This might happen if
     * the scanner does not support buffering, the number specified is more than what the
     * scanner is supposed to handle, or if a call to {@link #flush()} has resulted in the
     * buffer being short-circuited ahead of time, thus preventing us from reaching back.
     */
    void rewind(int count) throws ScannerException;

    /**
     * This method will return the cursor position, i.e. the number of characters processed
     * so far from the beginning of the document.
     * @return the cursor position
     */
    long getCursor();

    /**
     * @return current capacity of the buffer, or {@code 0} if buffering is not supported
     */
    long getBufferCapacity();

}
