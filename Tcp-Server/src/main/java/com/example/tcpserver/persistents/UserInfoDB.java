package com.example.tcpserver.persistents;

import com.example.tcpserver.entities.UserInfo;
import com.example.tcpserver.utils.WriteToFile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserInfoDB {
    static Connection connection;

    public static void saveUserInfoDB(UserInfo entity) throws SQLException {
        String SQL = "INSERT INTO USER_INFO ( init, bytes, frame, crc, end, idade, peso, altura, tamanho_nome, nome) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try{
            Class.forName("org.h2.Driver");
            String URL = "jdbc:h2:mem:~/segware";
            String user = "sa";
            String password = "";
            connection = DriverManager.getConnection(URL, user, password);
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, entity.getInit());
            stmt.setInt(2, entity.getBytes());
            stmt.setInt(3, entity.getFrame());
            stmt.setInt(4, entity.getCrc());
            stmt.setInt(5, entity.getEnd());
            stmt.setInt(6, entity.getIdade());
            stmt.setInt(7, entity.getPeso());
            stmt.setInt(8, entity.getAltura());
            stmt.setInt(9, entity.getTamanhoNome());
            stmt.setString(10, entity.getNome());
            stmt.execute();

            WriteToFile write = new WriteToFile();
            write.WriteObjectToFile(entity.toString(), "TCPServer-UserInfo-log");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static UserInfo getUserInfoById(Integer obtId){
        UserInfo entity = new UserInfo();

        try{
            ResultSet resultSet;
            Class.forName("org.h2.Driver");
            String URL = "jdbc:h2:mem:~/segware";
            String user = "sa";
            String password = "";
            String SQL = "SELECT * FROM USER_INFO WHERE id = ?";
            connection = DriverManager.getConnection(URL, user, password);
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1,obtId);

            resultSet = stmt.executeQuery();

            ArrayList<UserInfo> re = ResultSetPropertiesSimplifyHelps
                    .putResult(resultSet, UserInfo.class);

            entity = re.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entity;

    }
}
