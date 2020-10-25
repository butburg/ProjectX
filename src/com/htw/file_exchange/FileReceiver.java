package com.htw.file_exchange;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface FileReceiver {
    /**
     * Receive a content from input stream and writes it to a new local file
     *
     * @param filename local filename
     * @param is       content stream from provider
     */
    void receivedFile(String filename, InputStream is) throws IOException;
}
