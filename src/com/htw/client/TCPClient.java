package com.htw.client;

import java.io.*;
import java.net.Socket;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class TCPClient {
    private String name = "localhost";
    private int port = 8080;
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    public TCPClient(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public TCPClient(int port) {
        this.port = port;
    }

    public TCPClient() {}

    public void start() {
        try {
            socket = new Socket(name, port);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            System.out.println("Client is up");
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void sendMsg(String msg) {
        //schicke via Socket Stream an die Gegenstelle einen Datenstrom
        PrintWriter ps = new PrintWriter(os, true);
        ps.println(msg);
    }

    public void readMsg() {
        BufferedReader br = new BufferedReader(new InputStreamReader(is), 4);
        try {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        try {
            os.close();
            is.close();
            socket.close();
            System.out.println("Client end");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        //starte den Client
        try {
            TCPClient c1 = new TCPClient(args[1], Integer.parseInt(args[0]));
            c1.start();
            c1.sendMsg("Hi Server, ich bin Client");
            c1.readMsg();
            c1.close();
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }

    }

}
