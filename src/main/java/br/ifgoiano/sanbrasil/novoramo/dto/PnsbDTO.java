package br.ifgoiano.sanbrasil.novoramo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class PnsbDTO {
    //Nível Territorial (Código)
    @JsonAlias("NC")
    private String codigoNivelTerritorial;

    //Nível Territorial
    @JsonAlias("NN")
    private String nivelTerritorial;

    //Unidade de Medida (Código)
    @JsonAlias("MC")
    private String codigoUnidadeMedida;

    //Unidade de Medida
    @JsonAlias("MN")
    private String unidadeMedida;

    //Valor
    @JsonAlias("V")
    private String valor;

    //Brasil, Grande Região e UF (Código)
    @JsonAlias("D1C")
    private String codigoBrGrUf;

    //Brasil, Grande Região e UF
    @JsonAlias("D1N")
    private String brGrUf;

    //Variável (Código)
    @JsonAlias("D2C")
    private String codigoVariavel;

    //Variável
    @JsonAlias("D2N")
    private String variavel;

    //Ano (Código)
    @JsonAlias("D3C")
    private String codigoAno;

    //Ano
    @JsonAlias("D3N")
    private String ano;

    //Tipo de doença (Código)
    @JsonAlias("D4C")
    private String codigoTipoDoenca;

    //Tipo de doença
    @JsonAlias("D4N")
    private String tipoDoenca;
}
