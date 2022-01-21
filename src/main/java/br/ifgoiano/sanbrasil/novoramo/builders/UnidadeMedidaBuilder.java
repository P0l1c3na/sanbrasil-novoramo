package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.model.UnidadeMedida;

public final class UnidadeMedidaBuilder {
    private Integer codigo;
    private String nome;

    private UnidadeMedidaBuilder() {
    }

    public static UnidadeMedidaBuilder anUnidadeMedida() {
        return new UnidadeMedidaBuilder();
    }

    public UnidadeMedidaBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public UnidadeMedidaBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UnidadeMedida build() {
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setCodigo(codigo);
        unidadeMedida.setNome(nome);
        return unidadeMedida;
    }
}
