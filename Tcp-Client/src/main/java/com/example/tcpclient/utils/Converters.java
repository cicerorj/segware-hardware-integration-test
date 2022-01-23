package com.example.tcpclient.utils;

import com.example.tcpclient.dto.DefaultTextMessageDto;
import com.example.tcpclient.dto.DefaultUserInfoDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Converters {

    public static DefaultUserInfoDto convertUserDefaultDto(DefaultUserInfoDto messageDto){
        DefaultUserInfoDto messageDto2 = new DefaultUserInfoDto();
        var a = messageDto.getFRAME().replaceAll("0x","");
        var b = messageDto.getBYTES().replaceAll("0x","");
        var c = messageDto.getCRC().replaceAll("0x","");
        var e = messageDto.getEND().replaceAll("0x","");
        var f = messageDto.getINIT().replaceAll("0x","");
        var idade = messageDto.getIdade().replaceAll("0x","");
        var peso =messageDto.getPeso().replaceAll("0x","");
        var altura = messageDto.getAltura().replaceAll("0x","");
        var tamanhoNome = messageDto.getTamanhoNome().replaceAll("0x","");
        var nome = messageDto.getNome().replaceAll("0x","");

        var aa = a.replaceAll("\\s+","");
        var bb = b.replaceAll("\\s+","");
        var cc = c.replaceAll("\\s+","");
        var ee = e.replaceAll("\\s+","");
        var ff = f.replaceAll("\\s+","");
        var idade2 = idade.replaceAll("\\s+","");
        var peso2 = peso.replaceAll("\\s+","");
        var altura2 = altura.replaceAll("\\s+","");
        var tamanhoNome2 = tamanhoNome.replaceAll("\\s+","");
        var nome2 = nome.replaceAll("\\s+","");

        messageDto2.setINIT(ff);
        messageDto2.setFRAME(aa);
        messageDto2.setCRC(cc);
        messageDto2.setBYTES(bb);
        messageDto2.setEND(ee);
        messageDto2.setAltura(altura2);
        messageDto2.setNome(hexToAscii(nome2));
        messageDto2.setIdade(idade2);
        messageDto2.setPeso(peso2);
        messageDto2.setTamanhoNome(tamanhoNome2);

        return messageDto2;
    }

    public static DefaultTextMessageDto convertTextDefaultDto(DefaultTextMessageDto messageDto){
        DefaultTextMessageDto messageDto2 = new DefaultTextMessageDto();
        var a = messageDto.getFRAME().replaceAll("0x","");
        var b = messageDto.getBYTES().replaceAll("0x","");
        var c = messageDto.getCRC().replaceAll("0x","");
        var e = messageDto.getEND().replaceAll("0x","");
        var f = messageDto.getINIT().replaceAll("0x","");

        var aa = a.replaceAll("\\s+","");
        var bb = b.replaceAll("\\s+","");
        var cc = c.replaceAll("\\s+","");
        var ee = e.replaceAll("\\s+","");
        var ff = f.replaceAll("\\s+","");

        messageDto2.setINIT(ff);
        messageDto2.setFRAME(aa);
        messageDto2.setCRC(cc);
        messageDto2.setBYTES(bb);
        messageDto2.setEND(ee);
        messageDto2.setTextMessage(hexToAscii(messageDto.getTextMessage()));

        return messageDto2;
    }

    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        CollectionType listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }
}
