/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package middleware;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Middleware object that will help send packets to the server and client
 *
 * @author Jesús Ricardo Ortega Sánchez | 00000216703 Luis Gonzalo Cervantes
 * Rivera | 00000228549
 */
public class Middleware {

    /**
     * Sends DatagramPacket to the server.
     *
     * @param buffer bytes that will be sent
     * @param udpSocket socket that sends the packet
     * @throws IOException IOException
     */
    public void sendToServer(byte[] buffer, DatagramSocket udpSocket) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 1001);
        udpSocket.send(packet);
    }

    /**
     * Sends DatagramPacket to the client.
     *
     * @param buffer bytes that will be sent
     * @param udpSocket socket that sends the packet
     * @param receivedPacket DatagramPacket from which we will get the Address
     * and Port from which the client sent it
     * @throws IOException IOException
     */
    public void sendToClient(byte[] buffer, DatagramSocket udpSocket, DatagramPacket receivedPacket) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receivedPacket.getAddress(), receivedPacket.getPort());
        udpSocket.send(packet);
    }
}
