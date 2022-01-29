package br.ifgoiano.sanbrasil.novoramo.builders;

import br.ifgoiano.sanbrasil.novoramo.bean.to.DoencaTO;

public final class DoencaTOBuilder {
    private Integer codigo;
    private String nome;

    private DoencaTOBuilder() {
    }

    public static DoencaTOBuilder aDoencaTO() {
        return new DoencaTOBuilder();
    }

    public DoencaTOBuilder withCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public DoencaTOBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public DoencaTO build() {
        DoencaTO doencaTO = new DoencaTO();
        doencaTO.setCodigo(codigo);
        doencaTO.setNome(nome);
        return doencaTO;
    }
}
