package com.example.tcpclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultUserInfoDto {
    private String INIT;
    private String BYTES;
    private String FRAME;
    private String idade;
    private String peso;
    private String altura;
    private String tamanhoNome;
    private String nome;
    private String CRC;
    private String END;

}
