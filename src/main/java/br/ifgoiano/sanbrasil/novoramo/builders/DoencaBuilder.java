package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.Doenca;

public final class DoencaBuilder {
    private Integer codigo;
    private String nome;

    private DoencaBuilder() {
    }

    public static DoencaBuilder aDoenca() {
        return new DoencaBuilder();
    }

    public DoencaBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public DoencaBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Doenca build() {
        Doenca doenca = new Doenca();
        doenca.setCodigo(codigo);
        doenca.setNome(nome);
        return doenca;
    }
}
