package com.htw.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface Connection {
    /**
     * @return output stream of created connection
     */
    OutputStream getOutputStream() throws IOException;

    /**
     * @return input stream of created connection
     */
    InputStream getInputStream() throws IOException;
}
