package com.htw.app;

import com.htw.sensordata.Sensordata;
import com.htw.sensordata.SensordataExchanger;
import com.htw.sensordata.SensordataReceiver;
import com.htw.sensordata.SensordataTransmitter;
import com.htw.tcp.Client;
import com.htw.tcp.Connection;
import com.htw.tcp.Server;
import com.htw.tcp.TCPConnector;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class TCPSensordataExchangerImpl implements TCPSensordataExchanger {
    /**
     * Send sensordata to a specific host
     *
     * @param data
     * @param hostname
     * @param port
     */
    @Override
    public void sendSensordata(Sensordata data, String hostname, int port) throws IOException {
        //connection
        Client c1 = new TCPConnector();
        Connection conn = c1.connect(hostname, port);

        //send data
        SensordataTransmitter sensordataTransmitter = new SensordataExchanger();
        sensordataTransmitter.sendSensordata(data, conn.getOutputStream());

    }

    /**
     * Listen at port and receive sensor data
     *
     * @param port
     */
    @Override
    public void receiveSensordata(int port) throws IOException {
        //connection
        Server s1 = new TCPConnector();
        Connection conn = s1.acceptConnection(port);

        //send data
        SensordataReceiver sensordataReceiver = new SensordataExchanger();
        sensordataReceiver.receiveSensordata(conn.getInputStream());
    }
}
