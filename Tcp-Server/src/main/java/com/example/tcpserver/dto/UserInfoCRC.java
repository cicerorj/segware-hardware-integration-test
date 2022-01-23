package com.example.tcpserver.dto;

import com.example.tcpserver.entities.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoCRC {

    private String bytes;
    private String frame;
    private String idade;
    private String peso;
    private String altura;
    private String tamanhoNome;
    private String nome;

    public UserInfoCRC converter (DefaultUserInfoDto dto){
        UserInfoCRC entity = new UserInfoCRC();
        entity.setBytes(dto.getBYTES());
        entity.setFrame(dto.getFRAME());
        entity.setAltura(dto.getAltura());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setTamanhoNome(dto.getTamanhoNome());
        entity.setPeso(dto.getPeso());

        return entity;
    }
}
