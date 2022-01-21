package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.*;

public final class PesquisaBuilder {
    private Long id;
    private Doenca doenca;
    private Territorio territorio;
    private Variavel variavel;
    private UnidadeMedida unidadeMedida;
    private BrasilGrandeRegiaoUf brasilGrandeRegialUf;
    private Long valor;
    private Integer ano;

    private PesquisaBuilder() {
    }

    public static PesquisaBuilder aPesquisa() {
        return new PesquisaBuilder();
    }

    public PesquisaBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PesquisaBuilder withDoenca(Doenca doenca) {
        this.doenca = doenca;
        return this;
    }

    public PesquisaBuilder withTerritorio(Territorio territorio) {
        this.territorio = territorio;
        return this;
    }

    public PesquisaBuilder withVariavel(Variavel variavel) {
        this.variavel = variavel;
        return this;
    }

    public PesquisaBuilder withUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        return this;
    }

    public PesquisaBuilder withValor(Long valor) {
        this.valor = valor;
        return this;
    }

    public PesquisaBuilder withAno(Integer ano) {
        this.ano = ano;
        return this;
    }

    public PesquisaBuilder withBrasilGrandeRegialUf(BrasilGrandeRegiaoUf brasilGrandeRegialUf) {
        this.brasilGrandeRegialUf = brasilGrandeRegialUf;
        return this;
    }

    public Pesquisa build() {
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(id);
        pesquisa.setDoenca(doenca);
        pesquisa.setTerritorio(territorio);
        pesquisa.setVariavel(variavel);
        pesquisa.setUnidadeMedida(unidadeMedida);
        pesquisa.setValor(valor);
        pesquisa.setAno(ano);
        pesquisa.setBrasilGrandeRegialUf(brasilGrandeRegialUf);
        return pesquisa;
    }
}
