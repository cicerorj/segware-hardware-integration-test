package com.example.tcpserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TextMessageCRC {

    private String bytes;
    private String frame;
    private String textMessage;

    public TextMessageCRC converter (DefaultTextMessageDto dto){
        TextMessageCRC entity = new TextMessageCRC();
        entity.setBytes(dto.getBYTES());
        entity.setFrame(dto.getFRAME());
        entity.setTextMessage(dto.getTextMessage());

        return entity;
    }
}
