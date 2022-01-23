package com.example.tcpserver.utils;

import com.example.tcpserver.dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CRC8 {
    public static byte crc8(byte[] data){
        byte crcInit = 0;
        byte poly = 0x07;
        byte crc;
        byte polynom;
        int i;

        crc = crcInit;
        for (byte b : data) {
            crc = (byte)(b ^ crc);
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x80) != 0) {
                    crc = (byte)((crc << 1) ^ poly);
                } else {
                    crc <<= 1;
                }
            }
            crc &= 0xFF;
        }
        return crc;
    }

    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    public static UserInfoCRC convertUserDefaultDto(DefaultUserInfoDto messageDto){
        UserInfoCRC messageDto2 = new UserInfoCRC();
        messageDto2 = messageDto2.converter(messageDto);

        return messageDto2;
    }

    public static TextMessageCRC convertTextDefaultDto(DefaultTextMessageDto messageDto){
        TextMessageCRC messageDto2 = new TextMessageCRC();
        messageDto2 = messageDto2.converter(messageDto);

        return messageDto2;
    }
    public static String getDataHoraCRC (DataHoraCRC dto ) {
        CRC8 crc8 = new CRC8();
        dto.setDate(crc8.insertDate(dto.getDate()));
        String concat = dto.getBytes()
                .concat(dto.getFrame())
                .concat(dto.getDate());

        var stringAsci22 = hexToAscii(concat);
        var crcValue = crc8(stringAsci22.getBytes());

        return String.format("0x%02x ", crcValue);
    }
    public String insertDate(String tz){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of(tz));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        return zonedDateTimeOf.format(formatter);
    }
    public static String getTextMessageCRC (TextMessageCRC dto ) {
        CRC8 crc8 = new CRC8();
        String concat = dto.getBytes()
                .concat(dto.getFrame())
                .concat(dto.getTextMessage());

        var stringAsci22 = hexToAscii(concat);
        var crcValue = crc8(stringAsci22.getBytes());

        var s = String.format("0x%02x ", crcValue);
        var returnValue = s.replaceAll("0x","");

        return returnValue.replace(" ", "");
    }

  public static String getUserInfoCRC (UserInfoCRC dto ) {
        CRC8 crc8 = new CRC8();
        String concat = dto.getBytes()
                .concat(dto.getFrame())
                .concat(dto.getIdade())
                .concat(dto.getPeso())
                .concat(dto.getAltura())
                .concat(dto.getTamanhoNome())
                .concat(dto.getNome());

        var stringAsci22 = hexToAscii(concat);
        var crcValue = crc8(stringAsci22.getBytes());

      var s = String.format("0x%02x ", crcValue);
      var returnValue = s.replaceAll("0x","");

      return returnValue.replace(" ", "");

  }
}

