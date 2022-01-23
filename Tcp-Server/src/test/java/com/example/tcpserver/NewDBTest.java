package com.example.tcpserver;

import com.example.tcpserver.entities.TextMessage;
import com.example.tcpserver.entities.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
public class NewDBTest {
    String SQL_INSERT_USER_INFO = "INSERT INTO USER_INFO ( init, bytes, frame, crc, end, idade, peso, altura, tamanho_nome, nome) VALUES(?,?,?,?,?,?,?,?,?,?)";

    String SQL_INSERT_TEXT_MESSAGE = "INSERT INTO TEXT_MESSAGE (init, bytes, frame, crc, end, text_message, date) VALUES(?,?,?,?,?,?,?)";

    @Test
    public void persistTextMessage() throws Exception{
        TextMessage mocked = mock(TextMessage.class);
        var entity = new TextMessage();
        when(mocked.getInit()).thenReturn(entity.getInit());
        when(mocked.getBytes()).thenReturn(entity.getBytes());
        when(mocked.getFrame()).thenReturn(entity.getFrame());
        when(mocked.getCrc()).thenReturn(entity.getCrc());
        when(mocked.getEnd()).thenReturn(entity.getEnd());
        when(mocked.getTextMessage()).thenReturn(entity.getTextMessage());
        when(mocked.getDate()).thenReturn(entity.getDate());

        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(SQL_INSERT_TEXT_MESSAGE)).thenReturn(stmt);
    }

    @Test
    public void persistUser() throws Exception{
        UserInfo mocked = mock(UserInfo.class);
        var entity = new UserInfo();
        when(mocked.getInit()).thenReturn(entity.getInit());
        when(mocked.getBytes()).thenReturn(entity.getBytes());
        when(mocked.getFrame()).thenReturn(entity.getFrame());
        when(mocked.getCrc()).thenReturn(entity.getCrc());
        when(mocked.getEnd()).thenReturn(entity.getEnd());
        when(mocked.getIdade()).thenReturn(entity.getIdade());
        when(mocked.getPeso()).thenReturn(entity.getPeso());
        when(mocked.getAltura()).thenReturn(entity.getAltura());
        when(mocked.getTamanhoNome()).thenReturn(entity.getTamanhoNome());
        when(mocked.getNome()).thenReturn(entity.getNome());

        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(SQL_INSERT_USER_INFO)).thenReturn(stmt);
        connection.close();
    }
}
