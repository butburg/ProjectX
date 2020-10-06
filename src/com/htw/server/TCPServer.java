package com.htw.server;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Edwin W (570900) on Okt 2020
 *
 * stellt einen Server auf dem Rechner dar, der aufgerufen werden kann.
 *
 */
public class TCPServer {

    /**
     * der Standardport, wenn keiner explizit im Konstruktor angegeben wird
     */
    private int port = 4272;

    /**
     * Konstruktor der Server - Klasse
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


    /**
     * die eigentliche Methode, die den Server "hochfährt" und bisher auch wieder schließt
     * @throws IOException
     * @throws InterruptedException
     */
    public void up() throws IOException, InterruptedException {

        //öffne Socket mit Port, wartet auf Anfragen
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();

        //gebe in Konsole aus: was bekommt der Socket von der Gegenstelle als Stream
        InputStream is = socket.getInputStream();
        byte[] readBuffer = new byte[246];
        is.read(readBuffer);

        System.out.println("input:" + new String(readBuffer));

        //schicke via Socketstream an die Gegenstelle einen Datenstrom
        OutputStream os = socket.getOutputStream();
        String someText = "Edwin";
        os.write(someText.getBytes());
        System.out.println("send output:" + someText);

        //warte eine Zeit, schließe dann den offenen Outputstream
        Thread.sleep(5000);

        os.close();
    }

}
