package br.ifgoiano.sanbrasil.novoramo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Doenca doenca;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Territorio territorio;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Variavel variavel;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private UnidadeMedida unidadeMedida;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private BrasilGrandeRegiaoUf brasilGrandeRegialUf;

    private Long valor;

    private Integer ano;

}
