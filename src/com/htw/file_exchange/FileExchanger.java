package com.htw.file_exchange;

import java.io.*;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class FileExchanger implements FileTransmitter, FileReceiver {

    @Override
    public void sendFile(String filename, OutputStream os) throws IOException {
        // open file for stream
        FileInputStream fis = new FileInputStream(filename);

        this.streamData(fis, os);
        os.close();
    }

    @Override
    public void receivedFile(String filename, InputStream is) throws IOException {
        // prepare write file for stream
        FileOutputStream fos = new FileOutputStream(filename);
        this.streamData(is, fos);

    }

    /**
     * writes one stream into another one
     *
     * @param is the source
     * @param os the target sink
     * @throws IOException when there occur a read or write problem
     */
    private void streamData(InputStream is, OutputStream os) throws IOException {
        // receive from outputstream
        int tmpRead = 0;
        do {
            tmpRead = is.read();
            if (tmpRead != -1) {
                os.write(tmpRead);
            }
        } while (tmpRead != -1);
    }

}
