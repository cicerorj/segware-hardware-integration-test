package com.example.tcpserver.utils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteToFile {

    public void WriteObjectToFile(Object serObj, String nomeArquivo) {

        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\teste\\"+nomeArquivo+".txt", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.flush();
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
