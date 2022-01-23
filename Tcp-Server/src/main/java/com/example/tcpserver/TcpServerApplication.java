package com.example.tcpserver;

import com.example.tcpserver.configs.TCPGetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class TcpServerApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(TcpServerApplication.class, args);

        ServerSocket s = new ServerSocket(5005);
        while (true) {
            Socket socket = s.accept();
            System.out.println ("Socket [" + socket + "] criado.");
            TCPGetMessage getMessage = new TCPGetMessage();
            getMessage.GetMessage(socket);
            Thread thread = new Thread(getMessage);
            thread.start();
        }
    }

}
