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
 *
 * @author luisg
 */
public class Middleware {
    
    public void sendToServer(byte[] buffer, DatagramSocket udpSocket) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 1001);
        udpSocket.send(packet);
    }
    
    public void sendToClient(byte[] buffer, DatagramSocket udpSocket, DatagramPacket receivedPacket) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receivedPacket.getAddress(), receivedPacket.getPort());
        udpSocket.send(packet);
    }
}
