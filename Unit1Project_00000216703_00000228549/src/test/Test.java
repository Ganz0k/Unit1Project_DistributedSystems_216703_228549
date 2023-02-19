/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test test = new Test();
        Middleware middleware = new Middleware();
        Person person = new Person("Luis Gonzalo Cervantes Rivera", 69f, 1.73f);
        Gson gson = new Gson();
        String json = gson.toJson(person);
        
        try {
            test.clearJsonFile();
            
            PrintWriter output = new PrintWriter(new FileWriter("jsonFiles\\person.json"));
            output.println(json);
            output.flush();
            
            DatagramSocket udpSocket = new DatagramSocket();
            File fileSent = new File("jsonFiles\\person.json");
            byte[] buffer = new byte[(int) Files.size(fileSent.toPath())];
            buffer = Files.readAllBytes(fileSent.toPath());
            
            middleware.sendToServer(buffer, udpSocket);
            output.close();
            
            byte[] buffer2 = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(buffer2, buffer2.length, InetAddress.getLocalHost(), 1001);
            
            System.out.println("Waiting for server to reply...");
            udpSocket.receive(receivedPacket);
            
            System.out.println("Server replied back");
            buffer2 = receivedPacket.getData();
            Path path = Paths.get("jsonFiles\\person.json");
            test.clearJsonFile();
            Files.write(path, buffer2);
            
            Scanner scanner = new Scanner(new FileReader("jsonFiles\\person.json"));
            String resultJson = scanner.nextLine();
            
            Person resultPerson = gson.fromJson(resultJson, Person.class);
            System.out.println(resultPerson);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
    
    public void clearJsonFile() throws IOException {
        PrintWriter eraser = new PrintWriter(new FileWriter("jsonFiles\\person.json"));
        eraser.close();
    }
}
