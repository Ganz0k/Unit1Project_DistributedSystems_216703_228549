/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luisg
 */
public class DatagramServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(1001);
            byte[] buffer = new byte[25000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            System.out.println("Waiting to receive...");
            udpSocket.receive(packet);
            
            //TODO the rest lmao
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
