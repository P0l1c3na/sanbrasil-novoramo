package br.ifgoiano.sanbrasil.novoramo.bean.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Data
@Component
@SessionScope
public class UsuarioForm implements Serializable {

    private String email;

    private String senha;

    private String confirmacaoSenha;

    private String nome;

    private String telefone;

    private String mensagem;

    private Boolean falhou;
}
