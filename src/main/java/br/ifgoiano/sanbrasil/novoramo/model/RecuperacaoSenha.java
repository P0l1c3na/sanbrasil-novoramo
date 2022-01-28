package br.ifgoiano.sanbrasil.novoramo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class RecuperacaoSenha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Usuario usuario;

    private Integer codigoVerificacao;

    private LocalDateTime dataHoraSolicitacao;

    private Boolean realizada;

}
