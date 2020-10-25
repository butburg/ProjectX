package com.htw.tcp;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface Client {
    /**
     * will connect to another process (via network) with tcp
     *
     * @param hostname
     * @param port
     * @return
     */
    Connection connect(String hostname, int port) throws IOException;
}
