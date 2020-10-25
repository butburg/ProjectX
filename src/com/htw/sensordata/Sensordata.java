package com.htw.sensordata;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface Sensordata {
    /**
     * @return timestamp of measurement
     */
    long getTimeStamp();

    /**
     * @return value of measurement
     */
    float getValue();

    /**
     * @return sensor name
     */
    String getSensorName();
}
