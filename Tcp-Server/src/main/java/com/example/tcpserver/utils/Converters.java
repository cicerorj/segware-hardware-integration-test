package com.example.tcpserver.utils;


import com.example.tcpserver.dto.DefaultTextMessageDto;
import com.example.tcpserver.dto.DefaultUserInfoDto;

public class Converters {

    public static DefaultUserInfoDto convertUserDefaultDto(DefaultUserInfoDto messageDto){
        DefaultUserInfoDto messageDto2 = new DefaultUserInfoDto();
        messageDto2 = messageDto;
        messageDto2.setNome(hexToAscii(messageDto.getNome()));

        return messageDto2;
    }

    public static DefaultTextMessageDto convertTextDefaultDto(DefaultTextMessageDto messageDto){
        DefaultTextMessageDto messageDto2 = new DefaultTextMessageDto();
        messageDto2 = messageDto;
        messageDto2.setTextMessage(hexToAscii(messageDto.getTextMessage()));

        return messageDto2;
    }

    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

}
