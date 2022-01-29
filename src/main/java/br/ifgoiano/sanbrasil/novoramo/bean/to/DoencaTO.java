package br.ifgoiano.sanbrasil.novoramo.bean.to;

import br.ifgoiano.sanbrasil.novoramo.builders.DoencaBuilder;
import br.ifgoiano.sanbrasil.novoramo.builders.DoencaTOBuilder;
import br.ifgoiano.sanbrasil.novoramo.model.Doenca;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DoencaTO implements Serializable {

    private Integer codigo;

    private String nome;

    private List<DoencaTO> listaDoencas;

    public static List<DoencaTO> toListaDoencaTO(List<Doenca> doencas) {
        var lista = new ArrayList<DoencaTO>();
        doencas.forEach(doenca ->
                lista.add(DoencaTOBuilder.aDoencaTO()
                        .withNome(doenca.getNome())
                        .withCodigo(doenca.getCodigo())
                        .build())
        );
        return lista;
    }
}
