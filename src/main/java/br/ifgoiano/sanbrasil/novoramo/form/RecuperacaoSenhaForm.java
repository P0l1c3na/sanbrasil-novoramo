package br.ifgoiano.sanbrasil.novoramo.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
@Data
public class RecuperacaoSenhaForm implements Serializable {

   private String email;

   private Boolean falhou;

   private Boolean enviouCodigo;

   private Integer codigoRecuperacao;

   private Boolean codigoValido;

   private String mensagem;

   private String senha;

   private String confirmacaoSenha;

}
