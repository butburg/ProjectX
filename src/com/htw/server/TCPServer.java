package com.htw.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Edwin W (570900) on Okt 2020
 * <p>
 * stellt einen Server auf dem Rechner dar, der aufgerufen werden kann.
 */
public class TCPServer {
    private static final int PORT = 4272;
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
        try {
            is = socket.getInputStream();
            byte[] readBuffer = new byte[300];
            is.read(readBuffer);
            System.out.println(new String(readBuffer));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendFile(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);

            int tmpRead = 0;
            do {
                tmpRead = fis.read();
                if (tmpRead != -1) {
                    os.write(tmpRead);
                }
            } while (tmpRead != -1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveFile() {
        DataInputStream dis = new DataInputStream(is);
        String filename = "";
        try {
            filename = dis.readUTF();
            FileOutputStream fos = new FileOutputStream("new"+filename);
            int tmpRead;
            do {
                tmpRead = is.read();
                System.out.println("server receiving byte:" + tmpRead);
                if (tmpRead != -1) {
                    System.out.println("server receiving byte;" + tmpRead);
                    fos.write(tmpRead);
                }
            } while (tmpRead != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSensorData(String sensorname, float sensorvalue, long timestamp) {
        DataOutputStream dos = new DataOutputStream(os);
        try {
            dos.writeUTF(sensorname);
            dos.writeLong(timestamp);
            dos.writeFloat(sensorvalue);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("bad3");
        }
    }

    public Object[] receiveSensorData() {
        DataInputStream dis = new DataInputStream(is);
        String sensorname = "";
        long timestamp = -1;
        float sensorvalue = -1;
        try {
            sensorname = dis.readUTF();
            timestamp = dis.readLong();
            sensorvalue = dis.readFloat();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("bad4");
        }
        return new Object[]{sensorname, sensorvalue, timestamp};
    }

    public void close() {
        try {
            os.close();
            socket.close();
            System.out.println("Server end");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //starte den Server
        try {
            TCPServer s1 = new TCPServer(PORT);

            s1.start();

            Object[] sensorData = s1.receiveSensorData();
            System.out.println("received sensordate at server:");
            System.out.println(Arrays.toString(sensorData));

            s1.sendSensorData(((String) sensorData[0]), ((float) sensorData[1]), ((long) sensorData[2]));
            System.out.println("send sensordata back to client");

            s1.sendMsg("Server sagt Hallo zu Client");
            s1.readMsg();
            s1.readMsg();

            s1.receiveFile();
            System.out.println("file received at server");

            Thread.sleep(5000);

            s1.close();
        } catch (NumberFormatException | InterruptedException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
    }


}
