package br.ifgoiano.sanbrasil.novoramo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UnidadeMedida {

    @Id
    private Integer codigo;

    private String nome;
}
