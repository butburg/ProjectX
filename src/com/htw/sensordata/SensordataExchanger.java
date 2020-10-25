package com.htw.sensordata;

import java.io.*;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class SensordataExchanger implements SensordataReceiver, SensordataTransmitter {
    @Override
    public Sensordata receiveSensordata(InputStream is) throws IOException {
        DataInputStream dis = new DataInputStream(is);

        long timestamp = dis.readLong();
        float value = dis.readFloat();
        String sensorname = dis.readUTF();

        return new SensordataImpl(timestamp, value, sensorname);
    }

    @Override
    public void sendSensordata(Sensordata data, OutputStream os) throws IOException {
        DataOutputStream daos = new DataOutputStream(os);

        daos.writeLong(data.getTimeStamp());
        daos.writeFloat(data.getValue());
        daos.writeUTF(data.getSensorName());

        os.close();
    }
}
