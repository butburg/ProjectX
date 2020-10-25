package com.htw.sensordata;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface SensordataTransmitter {

    /**
     * sens Sensordata set
     *
     * @param data data to send
     * @param os   stream to recipient
     */
    void sendSensordata(Sensordata data, OutputStream os) throws IOException;
}
