package com.htw.client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

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

    public TCPClient() {
    }

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
        String newFilename = "";
        try {
            newFilename = dis.readUTF();
            FileOutputStream fos = new FileOutputStream(newFilename);
            int tmpRead = 0;
            do {
                tmpRead = is.read();
                System.out.println("client receiving byte:" + tmpRead);
                if (tmpRead != -1) {
                    fos.write(tmpRead);
                }
            } while (tmpRead != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String filename) {
        DataOutputStream dos = new DataOutputStream(os);
        try {
            FileInputStream fis = new FileInputStream(filename);
            dos.writeUTF(filename);
            int tmpRead;
            do {
                tmpRead = fis.read();
                System.out.println("client sends byte:" + tmpRead);
                if (tmpRead != -1) {
                    System.out.println("client sends byte;" + tmpRead);
                    os.write(tmpRead);
                }
            } while (tmpRead != -1);
            os.close();
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
            System.out.println("bad2");
            System.exit(0);
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
            System.out.println("bad1");
        }
        return new Object[]{sensorname, sensorvalue, timestamp};
    }

    public void close() {
        try {
            os.close();
            socket.close();
            System.out.println("Client end");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //starte den Client
        try {
            TCPClient c1 = new TCPClient(args[0], Integer.parseInt(args[1]));
            c1.start();

            System.out.println("1s sleep..");
            Thread.sleep(1000);

            c1.sendSensorData("sensor1", 213, System.currentTimeMillis());
            System.out.println("sensordata send from client");

            Object[] sensorData = c1.receiveSensorData();
            System.out.println("received at client: " + Arrays.toString(sensorData));


            Thread.sleep(2000);

            c1.sendMsg("i ");
            c1.sendMsg("schlau");
            c1.readMsg();

            c1.sendFile("testFiles/halloWelt.txt");
            System.out.println("file send from client");

            c1.close();

        } catch (NumberFormatException | InterruptedException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }

    }

}
