package com.example.tcpclient.configs;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPGetMessage implements Runnable{

    private InputStream inputStream;
    private Socket socket;
    private byte[] buffer;

    public TCPGetMessage(Socket socketServer) {
        this.socket = socketServer;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TCPGetMessage() {
    }

    public void run() {
        String str = "";
        while (true) {
            buffer = new byte[2048];
            int n = 0;
            try {
                n = inputStream.read(buffer);
                str = new String(buffer, 0, n);
                System.out.println ("Mensagem do Servidor : " + str);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (str.equals("q")) {
                break;
            }
        }
        try {
            if (socket != null)
                socket.close();
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {

        }
    }
}
