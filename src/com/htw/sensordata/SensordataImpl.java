package com.htw.sensordata;

/**
 * @author Edwin W (570900) on Okt 2020
 */
class SensordataImpl implements Sensordata {

    private final long timestamp;
    private final float value;
    private final String name;

    public SensordataImpl(long timestamp, float value, String name) {
        this.timestamp = timestamp;
        this.value = value;
        this.name = name;
    }

    /**
     * @return timestamp of measurement
     */
    @Override
    public long getTimeStamp() {
        return this.timestamp;
    }

    /**
     * @return value of measurement
     */
    @Override
    public float getValue() {
        return this.value;
    }

    /**
     * @return sensor name
     */
    @Override
    public String getSensorName() {
        return this.name;
    }
}
