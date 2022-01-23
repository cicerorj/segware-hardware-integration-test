package com.example.tcpserver.dto;

import com.example.tcpserver.entities.TextMessage;
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
public class DefaultTextMessageDto {

    private String INIT;
    private String BYTES;
    private String FRAME;//0xA1 0xA2
    private String textMessage;
    private String CRC;
    private String END;


    public TextMessage toEntity (DefaultTextMessageDto dto){
        TextMessage entity = new TextMessage();
        entity.setBytes(Integer.parseInt(dto.getBYTES(), 16));
        entity.setEnd(Integer.parseInt(dto.getEND(), 16));
        entity.setCrc(Integer.parseInt(dto.getCRC(), 16));
        entity.setFrame(Integer.parseInt(dto.getFRAME(), 16));
        entity.setInit(Integer.parseInt(dto.getINIT(), 16));
        entity.setTextMessage(dto.getTextMessage());
        entity.setDate(insertDate("America/Sao_Paulo"));

        return entity;
    }


    public String insertDate(String tz){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of(tz));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        return zonedDateTimeOf.format(formatter);
    }

}
