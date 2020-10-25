package com.htw.sensordata;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface SensordataReceiver {

    /**
     * Receive data from stream and create new sensor data object
     *
     * @param is Inputstream that delivers the data as stream
     * @return Sensordata set with the received data
     * @throws IOException
     */
    Sensordata receiveSensordata(InputStream is) throws IOException;
}
