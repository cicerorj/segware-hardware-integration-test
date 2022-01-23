package com.example.tcpserver.configs;

import com.example.tcpserver.dto.*;
import com.example.tcpserver.persistents.TextMessageDB;
import com.example.tcpserver.persistents.UserInfoDB;
import com.example.tcpserver.utils.CRC8;
import com.example.tcpserver.utils.Converters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

public class TCPGetMessage implements Runnable {

    public TCPGetMessage() {
    }

    private int remotePort = 5001;
    private String remoetAddress = "localhost";
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socketGet;
    private Socket socketSendMessage;
    private boolean socketIsExits = false;
    private int sum = 0;

    private byte[] buffer;

    public void GetMessage(Socket socket) {
        this.socketGet = socket;
        try {
            inputStream = socketGet.getInputStream();
            outputStream = socketGet.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataHoraDto stringToDataHoraDto(String  s ) throws JsonProcessingException {
        String json = s;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DataHoraDto dto = mapper.reader().forType(DataHoraDto.class)
                .readValue(json);
        return dto;
    }

    public DefaultUserInfoDto stringToUserInfo(String  s ) throws JsonProcessingException {
        String json = s;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DefaultUserInfoDto dto = mapper.reader().forType(DefaultUserInfoDto.class)
                .readValue(json);
        return dto;
    }
    public DefaultTextMessageDto stringToTextMessage(String  s ) throws JsonProcessingException {
        String json = s;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DefaultTextMessageDto dto = mapper.reader().forType(DefaultTextMessageDto.class)
                .readValue(json);
        return dto;
    }

    public String getFrameValueFromObject(Object obj){
        Set<Object> setObject = new LinkedHashSet<>();
        setObject.add(obj);
        String getFrameValue = setObject.toString();
        var frame = getFrameValue.indexOf("frame");
        return getFrameValue.substring(frame+9,frame+13);
    }

    public void run() {
        String str = "";
        int n = 0;
        int aux = 0;
        while (true) {

    try {
        buffer = new byte[2048];
        n = inputStream.read(buffer);
        str = new String(buffer, 0, n);
        var r1 = str.replaceAll("0x","");
        var r2 = r1.replaceAll("\\s+","");
        var frameValue = getFrameValueFromObject(str);

        aux++;
        if(str.contains("frame")){
            if( frameValue.contains("1")){

                DefaultTextMessageDto messageDto = new DefaultTextMessageDto();
                TextMessageCRC crcDto = new TextMessageCRC();

                messageDto = stringToTextMessage(r2);
                crcDto = CRC8.convertTextDefaultDto(messageDto);
                var crcValue = CRC8.getTextMessageCRC(crcDto);
                var crcToDb = Integer.parseInt(crcValue, 16);
                messageDto = Converters.convertTextDefaultDto(messageDto);
                var toSave = messageDto.toEntity(messageDto);
                toSave.setCrc(crcToDb);
                TextMessageDB.saveTextMessageDB(toSave);
                sendMessageWhenTextMessageIsSavedSuccess(toSave.getTextMessage());

            }

            if( frameValue.contains("2")){
                DefaultUserInfoDto userDto = new DefaultUserInfoDto();
                userDto = stringToUserInfo(r2);
                UserInfoCRC crcDto = new UserInfoCRC();
                crcDto = CRC8.convertUserDefaultDto(userDto);
                var crcValue = CRC8.getUserInfoCRC(crcDto);
                var crcToDb = Integer.parseInt(crcValue, 16);
                userDto = Converters.convertUserDefaultDto(userDto);

                var toSave = userDto.toEntity(userDto);
                toSave.setCrc(crcToDb);
                UserInfoDB.saveUserInfoDB(toSave);
                sendMessageWhenUserInfoIsSavedSuccess(crcDto.toString());
            }

            if( frameValue.contains("3")){
                DataHoraDto dto = new DataHoraDto();
                dto = stringToDataHoraDto(r2);
                DataHoraCRC crcDto = new DataHoraCRC();
                crcDto = crcDto.converter(dto);

                sendDateTimeMessage(crcDto);

            }
        }
        } catch (IOException e) {
            e.printStackTrace();
            break;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str.equals("q")) {
            break;
        }
    }
    try {
        if (socketGet != null)
            socketGet.close();
        if (inputStream != null)
            inputStream.close();
    } catch (Exception e) {

    }
    }

    public void sendDateTimeMessage(DataHoraCRC dto) throws IOException {
        if (socketIsExits) {
            try {
                String string = "".concat(dto.getDia()
                        .concat("/")
                        .concat(dto.getMes())
                        .concat("/")
                        .concat(dto.getAno())
                        .concat(" ")
                        .concat(dto.getHora())
                        .concat(":")
                        .concat(dto.getMinuto())
                        .concat(":")
                        .concat(dto.getSegundo())
                );
                outputStream.write(string.getBytes());
                //Envio da mensagem
                outputStream.flush();
            } catch (Exception e) {
                System.out.println ("Client Socket does not exist.");
                checkSocket();
            }
        } else {
            checkSocket();
        }
    }

    public void sendMessageWhenTextMessageIsSavedSuccess( String s ) throws IOException {
        if (socketIsExits) {
            try {
                String message = "A mensagem [" + s + "] foi recebida com sucesso.";
                outputStream.write(message.getBytes());
                //Envio da mensagem
                outputStream.flush();
            } catch (Exception e) {
                System.out.println ("Client Socket does not exist.");
                checkSocket();
            }
        } else {
            checkSocket();
        }
    }
    public void sendMessageWhenUserInfoIsSavedSuccess( String s ) throws IOException {
        if (socketIsExits) {
            try {
                String message = "A mensagem [" + s + "] foi recebida com sucesso.";
                outputStream.write(message.getBytes());
                //Envio da mensagem
                outputStream.flush();
            } catch (Exception e) {
                System.out.println ("Client Socket does not exist.");
                checkSocket();
            }
        } else {
            checkSocket();
        }
    }

    private void checkSocket() {
        try {
            socketSendMessage = new Socket(remoetAddress, remotePort);
            outputStream = socketSendMessage.getOutputStream();
            socketIsExits = true;
        } catch (Exception e) {
            socketIsExits = false;
        }
    }
}

