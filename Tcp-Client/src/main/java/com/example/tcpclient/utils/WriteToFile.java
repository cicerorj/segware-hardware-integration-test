package com.example.tcpclient.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WriteToFile {

    public  void write(String obj){

        try {
            FileWriter myWriter = new FileWriter("C:\\teste\\TCPClient-log.txt", true);
            myWriter.write(obj);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void WriteObjectToFile(Object serObj) {

        try {

            FileOutputStream fileOut = new FileOutputStream("C:\\teste\\filename2.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeUnicodeJava11(String fileName, List<String> lines) {

        try (FileWriter fw = new FileWriter(new File("C:\\teste\\filename2.txt"), StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fw)) {

            for (String line : lines) {
                writer.append(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
