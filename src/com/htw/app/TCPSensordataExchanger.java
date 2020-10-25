package com.htw.app;

import com.htw.sensordata.Sensordata;

import java.io.IOException;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface TCPSensordataExchanger {
    /**
     * Send sensordata to a specific host
     *
     * @param data
     * @param hostname
     * @param port
     */
    void sendSensordata(Sensordata data, String hostname, int port) throws IOException;


    /**
     * Listen at port and receive sensor data
     *
     * @param port
     */
    void receiveSensordata(int port) throws IOException;
}
