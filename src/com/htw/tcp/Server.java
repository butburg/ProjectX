package com.htw.tcp;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface Server {
    /**
     * will await and accept a connection from another process via tcp
     *
     * @param port using this port
     * @return the connection
     */
    Connection acceptConnection(int port) throws IOException;
}
