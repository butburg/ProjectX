package com.htw.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Edwin W (570900) on Okt 2020
 * <p>
 * stellt einen Server auf dem Rechner dar, der aufgerufen werden kann.
 */
public class TCPServer {
    /**
     * der Standardport, wenn keiner explizit im Konstruktor angegeben wird
     */
    private int port = 8080;
    private OutputStream os;
    private InputStream is;
    private Socket socket;
    private ServerSocket server;

    /**
     * Konstruktor der Server - Klasse
     *
     * @param port der zu nutzende Port
     */
    public TCPServer(int port) {
        this.port = port;
    }

    /**
     * alternativer Konstruktor mit Standardport
     */
    public TCPServer() {
    }

    public void start() {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is up at:");

            String address = "localhost"; //überschreiben, da server.getInetAddress().getHostAddress() LOKAL funktionslos ist
            System.out.println(address + ":" + port);

            socket = server.accept();
            System.out.println("Server accepted connection");

            os = socket.getOutputStream();
            is = socket.getInputStream();
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
        byte[] readBuffer = new byte[300];
        try {
            is.read(readBuffer);
            System.out.println(new String(readBuffer));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void receiveFile() {
        DataInputStream dis = new DataInputStream(is);
        FileOutputStream fos = null;
        try {
            byte[] sendBuffer = new byte[1000];
            fos = new FileOutputStream("test.txt");

            int read = 0;
            int totalRead = 0;

            while((read = dis.read(sendBuffer, 0, sendBuffer.length)) > 0) {
                totalRead += read;
                System.out.println("read " + totalRead + " bytes.");
                fos.write(sendBuffer, 0, read);
            }

            fos.close();
            dis.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        try {
            os.close();
            is.close();
            socket.close();
            System.out.println("Server end");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //starte den Server
        try {
            TCPServer s1 = new TCPServer(Integer.parseInt(args[0]));

            s1.start();
            s1.sendMsg("Server sagt Hallo zu Client");
            s1.readMsg();
            s1.receiveFile();
            Thread.sleep(5000);
            s1.close();
        } catch (NumberFormatException | InterruptedException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
    }


}
