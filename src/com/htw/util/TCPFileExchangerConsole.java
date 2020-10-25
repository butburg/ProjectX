package com.htw.util;


import com.htw.app.TCPFileExchange;
import com.htw.app.TCPFileExchangeImpl;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 * <p>
 * expected parameters:
 * ...when you want to send:
 * filename, hostname (recipient), port
 * ...when you want to receive
 * filename, port
 */
public class TCPFileExchangerConsole {
    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.err.println("Arguments required in specific form, e.g.: 'test.txt localhost 8080'");
            System.err.println("as Server: 'newFilename portX'");
            System.err.println("as client: 'sourceFilename hostname portX'");
            return;
        }

        String filename = args[0];
        String hostname = null;
        int port = -1;
        String portString = null;

        if (args.length == 3) {
            hostname = args[1];
            portString = args[2];
        } else if (args.length == 2) portString = args[1];

        port = Integer.parseInt(portString);

        TCPFileExchange tcpFileExchange = new TCPFileExchangeImpl();
        if (hostname == null) {
            // receive
            tcpFileExchange.receiveFile(filename, port);
        } else {
            // send
            tcpFileExchange.sendFileToHost(filename, hostname, port);
        }

    }
}
