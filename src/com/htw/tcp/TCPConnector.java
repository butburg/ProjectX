package com.htw.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class TCPConnector implements Client, Server, Connection {

    private Socket socket;

    @Override
    public Connection connect(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        return this;
    }

    @Override
    public Connection acceptConnection(int port) throws IOException {
        ServerSocket srvs = new ServerSocket(port);
        System.out.println("Server socket created");
        this.socket = srvs.accept();
        return this;
    }

    /**
     * @return output stream of created connection
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    /**
     * @return input stream of created connection
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }
}
