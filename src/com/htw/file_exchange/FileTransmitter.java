package com.htw.file_exchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface FileTransmitter {
    /**
     * Send file from a local program over an output stream to another process
     *
     * @param filename local filename
     * @param os       connection / stream to remote entity
     */
    void sendFile(String filename, OutputStream os) throws IOException;
}
