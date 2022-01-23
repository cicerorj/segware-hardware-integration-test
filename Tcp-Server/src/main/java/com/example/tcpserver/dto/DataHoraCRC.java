package com.example.tcpserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataHoraCRC {

    private String bytes;
    private String frame;
    private String date;
    private String dia;
    private String mes;
    private String ano;
    private String hora;
    private String minuto;
    private String segundo;



    public DataHoraCRC converter ( DataHoraDto dto) {
        DataHoraCRC entity = new DataHoraCRC();
        entity.setBytes(dto.getBYTES());
        entity.setFrame(dto.getFRAME());
        var timeZone = hexToAscii(dto.getDate());
        entity.setDate(insertDate(timeZone));



        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of(timeZone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        entity.setDia(String.valueOf(zonedDateTimeOf.getDayOfMonth()));
        entity.setMes(String.valueOf(zonedDateTimeOf.getMonthValue()));
        entity.setAno(String.valueOf(zonedDateTimeOf.getYear()));
        entity.setHora(String.valueOf(zonedDateTimeOf.getHour()));
        entity.setMinuto(String.valueOf(zonedDateTimeOf.getMinute()));
        entity.setSegundo(String.valueOf(zonedDateTimeOf.getSecond()));

        return entity;
    }

    public String insertDateDto(String tz){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of(tz));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");


        return zonedDateTimeOf.format(formatter);
    }

    public String insertDate(String tz){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of(tz));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");


        return zonedDateTimeOf.format(formatter);
    }

    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
