package com.htw.app;

import com.htw.file_exchange.FileExchanger;
import com.htw.file_exchange.FileReceiver;
import com.htw.file_exchange.FileTransmitter;
import com.htw.tcp.Client;
import com.htw.tcp.Connection;
import com.htw.tcp.Server;
import com.htw.tcp.TCPConnector;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class TCPFileExchangeImpl implements TCPFileExchange {
    @Override
    public void sendFileToHost(String filename, String hostname, int port) throws IOException {
        //connection
        Client c1 = new TCPConnector();
        Connection conn = c1.connect(hostname, port);

        //sendFile
        FileTransmitter fileTransmitter = new FileExchanger();
        fileTransmitter.sendFile(filename, conn.getOutputStream());

    }

    @Override
    public void receiveFile(String filename, int port) throws IOException {
        //connection
        Server srv = new TCPConnector();
        Connection conn = srv.acceptConnection(port);

        //receive File
        FileReceiver fileReceiver = new FileExchanger();
        fileReceiver.receivedFile(filename, conn.getInputStream());
    }
}
