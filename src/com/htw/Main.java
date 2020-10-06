package com.htw;

import com.htw.server.TCPServer;

import java.io.IOException;
/**
 * @author Edwin W (570900) on Okt 2020
 *
 * startet eine Server-Klasse auf dem Rechner
 *
 */
public class Main {

    public static void main(String[] args) {
        try {
            //starte den Server
            new TCPServer().up();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
