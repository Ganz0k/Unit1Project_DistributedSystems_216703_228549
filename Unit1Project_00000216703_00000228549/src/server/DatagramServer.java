/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
            
            while (true) {
                System.out.println("Waiting to receive...");
                udpSocket.receive(packet);
                
                System.out.println("A client has been connected");
                buffer = packet.getData();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } 
    }
    
    /**
     * 
     * @param weight
     * @param height
     * @return 
     */
    public float calculateBMI(float weight, float height) {
        return weight / (float) (Math.pow(height, 2));
    }
}
