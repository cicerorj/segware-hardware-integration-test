package com.example.tcpserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@EqualsAndHashCode(callSuper = true)
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int init;

    @Column
    private int bytes;

    @Column
    private int frame;

    @Column
    private int crc;

    @Column
    private int end;

    @Column
    private Integer idade;

    @Column
    private Integer peso;

    @Column
    private Integer altura;

    @Column
    private Integer tamanhoNome;

    @Column
    private String nome;


}
