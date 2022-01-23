package com.example.tcpclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
