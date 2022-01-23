package com.example.tcpserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class DataHora extends Default implements Serializable {

    private Date date;
}
