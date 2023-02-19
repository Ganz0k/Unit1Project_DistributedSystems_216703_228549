/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import middleware.Middleware;
import template.Person;

/**
 *
 * @author luisg
 */
public class DatagramServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        Middleware middleware = new Middleware();
        Gson gson = new Gson();
        DatagramServer dS = new DatagramServer();
        
        try {
            DatagramSocket udpSocket = new DatagramSocket(1001);
            byte[] buffer = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            
//            while (true) {
                System.out.println("Waiting to receive...");
                udpSocket.receive(receivedPacket);
                
                System.out.println("A client has been connected");
                buffer = receivedPacket.getData();
                Path path = Paths.get("jsonFiles\\output.json");
                Files.write(path, buffer);
                
                Scanner scanner = new Scanner(new FileReader("jsonFiles\\output.json"));
                String json = scanner.nextLine();
                
                dS.clearJsonFile();
                
                Person person = gson.fromJson(json, Person.class);
                float bmi = dS.calculateBMI(person.getWeight(), person.getHeight());
                person.setBmi(bmi);
                String result = dS.determineResult(bmi);
                person.setResult(result);
                
                PrintWriter output = new PrintWriter(new FileWriter("jsonFiles\\output.json"));
                String bmiJson = gson.toJson(person);
                output.println(bmiJson);
                output.flush();
                
                
                File fileSent = new File("jsonFiles\\output.json");
                byte[] buffer2 = new byte[(int) Files.size(fileSent.toPath())];
                buffer2 = Files.readAllBytes(fileSent.toPath());
                
                middleware.sendToClient(buffer2, udpSocket, receivedPacket);
                output.close();
//            }
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
        return weight / ((float) Math.pow(height, 2));
    }
    
    /**
     * 
     * @param bmi
     * @return 
     */
    public String determineResult(float bmi) {
        if (bmi < 18.5f) {
            return "Thin";
        } else if (bmi >= 18.6f && bmi <= 24.9f) {
            return "Healthy";
        } else if (bmi >= 25f && bmi <= 29.9f) {
            return "Overweight";
        } else if (bmi > 30f) {
            return "Obese";
        }
        
        return null;
    }
    
    public void clearJsonFile() throws IOException {
        PrintWriter eraser = new PrintWriter(new FileWriter("jsonFiles\\output.json"));
        eraser.close();
    }
}
