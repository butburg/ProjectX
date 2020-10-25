package com.htw.app;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface TCPFileExchange {
    /**
     * send a file from local entity to remote host via TCP
     *
     * @param filename
     * @param hostname
     * @param port
     */
    void sendFileToHost(String filename, String hostname, int port) throws IOException;

    /**
     * receive content from a TCP client and write it to a local file
     *
     * @param filename
     * @param port
     */
    void receiveFile(String filename, int port) throws IOException;
}
