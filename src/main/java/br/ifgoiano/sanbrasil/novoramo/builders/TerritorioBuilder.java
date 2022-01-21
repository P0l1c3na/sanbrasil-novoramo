package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.Territorio;

public final class TerritorioBuilder {
    private Integer codigo;
    private String nome;

    private TerritorioBuilder() {
    }

    public static TerritorioBuilder aTerritorio() {
        return new TerritorioBuilder();
    }

    public TerritorioBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public TerritorioBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Territorio build() {
        Territorio territorio = new Territorio();
        territorio.setCodigo(codigo);
        territorio.setNome(nome);
        return territorio;
    }
}
