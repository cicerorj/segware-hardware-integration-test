package com.example.tcpserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.*;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith({SpringExtension.class})
public class UserInfoTest {
    String SQL_INSERT_USER_INFO = "INSERT INTO USER_INFO ( init, bytes, frame, crc, end, idade, peso, altura, tamanho_nome, nome) VALUES(?,?,?,?,?,?,?,?,?,?)";
    String SQL_CREATE_USER_INFO = "CREATE TABLE USER_INFO (INIT INT, BYTES INT, FRAME INT, CRC INT, END INT, IDADE INT, PESO INT, ALTURA INT, TAMANHO_NOME INT, NOME VARCHAR)";

    String SQL_INSERT_TEXT_MESSAGE = "INSERT INTO TEXT_MESSAGE (init, bytes, frame, crc, end, text_message, date) VALUES(?,?,?,?,?,?,?)";
    String SQL_CREATE_TEXT_MESSAGE = "CREATE TABLE TEXT_MESSAGE (INIT INT, BYTES INT, FRAME INT, CRC INT, END INT, TEXT_MESSAGE VARCHAR, DATE VARCHAR)";

    @Test
    public void testDatabaseMemU() throws SQLException {
        testDatabaseUserInfo("jdbc:h2:mem:~/segware");
        testDatabaseTextMessage("jdbc:h2:mem:~/segware");
    }

    public void testDatabaseUserInfo(String url) throws SQLException {
        Connection connection= DriverManager.getConnection(url);
        Statement s=connection.createStatement();
        try {
            s.execute("DROP TABLE USER_INFO" );
        } catch(SQLException sqle) {
            System.out.println("Table not found, not dropping");
        }
        s.execute(SQL_CREATE_USER_INFO);
        PreparedStatement ps=connection.prepareStatement(SQL_INSERT_USER_INFO);
        ps.setInt(1, anyInt());
        ps.setInt(2, anyInt());
        ps.setInt(3, anyInt());
        ps.setInt(4, anyInt());
        ps.setInt(5, anyInt());
        ps.setInt(6, anyInt());
        ps.setInt(7, anyInt());
        ps.setInt(8, anyInt());
        ps.setInt(9, anyInt());
        ps.setString(10, anyString());
        ps.executeUpdate();

        ps.close();
        s.close();
        connection.close();
    }

    public void testDatabaseTextMessage(String url) throws SQLException {
        Connection connection= DriverManager.getConnection(url);
        Statement s=connection.createStatement();
        try {
            s.execute("DROP TABLE TEXT_MESSAGE" );
        } catch(SQLException sqle) {
            System.out.println("Table not found, not dropping");
        }
        s.execute(SQL_CREATE_TEXT_MESSAGE);
        PreparedStatement ps=connection.prepareStatement(SQL_INSERT_TEXT_MESSAGE);
        ps.setInt(1, anyInt());
        ps.setInt(2, anyInt());
        ps.setInt(3, anyInt());
        ps.setInt(4, anyInt());
        ps.setInt(5, anyInt());
        ps.setString(6, anyString());
        ps.setString(7, anyString());
        ps.executeUpdate();

        ps.close();
        s.close();
        connection.close();
    }

}
