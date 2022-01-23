package com.example.tcpserver.dto;

import com.example.tcpserver.entities.UserInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonDeserialize(as = DefaultUserInfoDto.class)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultUserInfoDto {
    private String init;
    private String BYTES;
    private String FRAME;
    private String idade;
    private String peso;
    private String altura;
    private String tamanhoNome;
    private String nome;
    private String CRC;
    private String END;


    public UserInfo toEntity (DefaultUserInfoDto dto){
        UserInfo entity = new UserInfo();
        entity.setBytes(Integer.parseInt(dto.getBYTES(), 16));
        entity.setEnd(Integer.parseInt(dto.getEND(), 16));
        entity.setCrc(Integer.parseInt(dto.getCRC(), 16));
        entity.setFrame(Integer.parseInt(dto.getFRAME(), 16));
        entity.setInit(Integer.parseInt(dto.getInit(), 16));
        entity.setAltura(Integer.parseInt(dto.getAltura(), 16));
        entity.setNome(dto.getNome());
        entity.setIdade(Integer.parseInt(dto.getIdade(), 16));
        entity.setTamanhoNome(Integer.parseInt(dto.getTamanhoNome(), 16));
        entity.setPeso(Integer.parseInt(dto.getPeso(), 16));

        return entity;
    }

}
