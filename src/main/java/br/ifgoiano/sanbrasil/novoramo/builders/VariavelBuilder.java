package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.Variavel;

public final class VariavelBuilder {
    private Integer codigo;
    private String nome;

    private VariavelBuilder() {
    }

    public static VariavelBuilder aVariavel() {
        return new VariavelBuilder();
    }

    public VariavelBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public VariavelBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Variavel build() {
        Variavel variavel = new Variavel();
        variavel.setCodigo(codigo);
        variavel.setNome(nome);
        return variavel;
    }
}
