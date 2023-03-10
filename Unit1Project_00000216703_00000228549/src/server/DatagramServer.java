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
 * Udp socket server that receives a person object parsed to a json file and
 * calculates their BMI and health result to then send it back to the client.
 *
 * @author Jesús Ricardo Ortega Sánchez | 00000216703 Luis Gonzalo Cervantes
 * Rivera | 00000228549
 */
public class DatagramServer {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Middleware middleware = new Middleware();
        Gson gson = new Gson();
        DatagramServer dS = new DatagramServer();

        try {
            dS.clearJsonFile();

            //First we create a DatagramSocket that will act as the server
            //listening on port 1001, alongside a byte array and a
            //DatagramPacket to receive
            DatagramSocket udpSocket = new DatagramSocket(1001);
            byte[] buffer = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                System.out.println("Waiting to receive...");
                udpSocket.receive(receivedPacket);

                //After it receives the packet it then proceeds to turn those
                //bytes into a json file named output.json
                System.out.println("A client has been connected");
                buffer = receivedPacket.getData();
                Path path = Paths.get("jsonFiles\\output.json");
                Files.write(path, buffer);

                //It then reads the json file and saves the data stored in it on
                //a String. Lastly it clears the file to later write something new
                Scanner scanner = new Scanner(new FileReader("jsonFiles\\output.json"));
                String json = scanner.nextLine();
                dS.clearJsonFile();

                //It parses the json format into a Person object, calculates its
                //BMI and health result
                Person person = gson.fromJson(json, Person.class);
                float bmi = dS.calculateBMI(person.getWeight(), person.getHeight());
                person.setBmi(bmi);
                String result = dS.determineResult(bmi);
                person.setResult(result);

                //Now it parses the Person file back to a json file but now with
                //its BMI and health result included
                PrintWriter output = new PrintWriter(new FileWriter("jsonFiles\\output.json"));
                String bmiJson = gson.toJson(person);
                output.println(bmiJson);
                output.flush();

                //It prepares a new byte array to convert the json file to bytes
                //and with the help of the Middleware it sends it back to the
                //client
                File fileSent = new File("jsonFiles\\output.json");
                byte[] buffer2 = new byte[(int) Files.size(fileSent.toPath())];
                buffer2 = Files.readAllBytes(fileSent.toPath());
                middleware.sendToClient(buffer2, udpSocket, receivedPacket);
                output.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Calculate's a person's BMI.
     *
     * @param weight weight of the person
     * @param height height of the person
     * @return BMI of the person
     */
    public float calculateBMI(float weight, float height) {
        return weight / ((float) Math.pow(height, 2));
    }

    /**
     * Determines the health result of a person by their BMI.
     *
     * @param bmi BMI of the person
     * @return Health result of the person
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

    /**
     * Clears the JSON file in case there was data already on it
     *
     * @throws IOException IOException
     */
    public void clearJsonFile() throws IOException {
        PrintWriter eraser = new PrintWriter(new FileWriter("jsonFiles\\output.json"));
        eraser.close();
    }
}
