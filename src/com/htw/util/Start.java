package com.htw.util;

import com.htw.client.TCPClient;
import com.htw.server.TCPServer;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class Start {
    public static void main(String[] args) {
        if (args.length == 2) {
            System.out.println("starte Client...");
            TCPClient.main(args);
        } else if (args.length == 0) {
            System.out.println("starte Server...");
            TCPServer.main(args);
        } else
            System.err.println("keine Args abgeben oder Host und Port angeben!");
    }
}
