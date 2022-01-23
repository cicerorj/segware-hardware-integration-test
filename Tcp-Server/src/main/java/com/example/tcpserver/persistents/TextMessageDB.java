package com.example.tcpserver.persistents;

import com.example.tcpserver.entities.TextMessage;
import com.example.tcpserver.utils.WriteToFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TextMessageDB {
    static Connection connection;

    public static void saveTextMessageDB(TextMessage entity){
        try{
            Class.forName("org.h2.Driver");
            String URL = "jdbc:h2:mem:~/segware";
            String user = "sa";
            String password = "";
            connection = DriverManager.getConnection(URL, user, password);

            String SQL = "INSERT INTO TEXT_MESSAGE ( init, bytes, frame, crc, end, text_message, date) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);

            stmt.setInt(1, entity.getInit());
            stmt.setInt(2, entity.getBytes());
            stmt.setInt(3, entity.getFrame());
            stmt.setInt(4, entity.getCrc());
            stmt.setInt(5, entity.getEnd());
            stmt.setString(6, entity.getTextMessage());
            stmt.setString(7, entity.getDate());
            stmt.execute();

            WriteToFile write = new WriteToFile();
            write.WriteObjectToFile(entity.toString(), "TCPServer-TextMessage-log");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
