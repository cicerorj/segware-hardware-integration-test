package com.example.tcpclient;

import com.example.tcpclient.configs.TCPGetMessage;
import com.example.tcpclient.configs.TCPSendMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class TcpClientApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(TcpClientApplication.class, args);

        TCPSendMessage tcpSendMessage = new TCPSendMessage();
        Thread thread2 = new Thread(tcpSendMessage);
        thread2.start();

        ServerSocket serverSocket = new ServerSocket(5001);
        while (true) {
            Socket socketServer = serverSocket.accept();
            TCPGetMessage getMessage = new TCPGetMessage(socketServer);
            Thread thread = new Thread(getMessage);
            thread.start();
        }

    }

}
