package com.example.tcpserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataHoraDto {

    private String init;
    private String BYTES;
    private String FRAME;
    private String date;
    private String CRC;
    private String END;


}
