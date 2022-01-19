package br.ifgoiano.sanbrasil.novoramo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Perfil {

    @Id
    @Getter
    private Long id;

    @Getter @Setter
    @Column(unique = true)
    @NotNull
    private String nome;

    public Perfil(Long id, @NotNull String nome) {
        this.id = id;
        this.nome = nome;
    }
}
