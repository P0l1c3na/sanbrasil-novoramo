package br.ifgoiano.sanbrasil.novoramo.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Variavel {

    @Id
    private Integer codigo;

    private String nome;
}
