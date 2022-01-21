package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.BrasilGrandeRegiaoUf;

public final class BrasilGrandeRegialUfBuilder {
    private Integer codigo;
    private String nome;

    private BrasilGrandeRegialUfBuilder() {
    }

    public static BrasilGrandeRegialUfBuilder aBrasilGrandeRegialUf() {
        return new BrasilGrandeRegialUfBuilder();
    }

    public BrasilGrandeRegialUfBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public BrasilGrandeRegialUfBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public BrasilGrandeRegiaoUf build() {
        BrasilGrandeRegiaoUf brasilGrandeRegialUf = new BrasilGrandeRegiaoUf();
        brasilGrandeRegialUf.setCodigo(codigo);
        brasilGrandeRegialUf.setNome(nome);
        return brasilGrandeRegialUf;
    }
}
