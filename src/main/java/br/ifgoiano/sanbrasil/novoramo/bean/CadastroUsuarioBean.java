package br.ifgoiano.sanbrasil.novoramo.bean;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.bean.form.UsuarioForm;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequestScoped
@Slf4j
public class CadastroUsuarioBean implements Serializable {

    private final UsuarioForm usuarioForm;
    private final UsuarioService usuarioService;

    @Inject
    public CadastroUsuarioBean(@Autowired UsuarioForm usuarioForm, @Autowired UsuarioService usuarioService) {
        this.usuarioForm = usuarioForm;
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario() {
        var usuarioCadastrado = usuarioService.findById(usuarioForm.getEmail());
        if (Objects.isNull(usuarioCadastrado)) {
            var stb = new StringBuilder();

            if (!usuarioService.validarSenha(stb, usuarioForm.getSenha(), usuarioForm.getConfirmacaoSenha()) || !validarNomeEmail(stb)) {
                usuarioForm.setFalhou(Boolean.TRUE);
                usuarioForm.setMensagem(stb.toString());
                this.redirectPage("/publico/cadastro-usuario.xhtml");
                return;
            }

            var usuario = usuarioService.createUser(usuarioForm.getEmail(),
                    usuarioForm.getSenha(),
                    Collections.singletonList(new Perfil(2L, PerfilUsuario.USUARIO.name())),
                    Boolean.TRUE,
                    usuarioForm.getNome(),
                    usuarioForm.getTelefone());

            if (Objects.nonNull(usuario)) {
                usuarioForm.setFalhou(Boolean.FALSE);
                this.redirectPage("/login.xhtml?cadastrado=true");
            }

        }
        usuarioForm.setFalhou(Boolean.TRUE);
        usuarioForm.setMensagem("Usuario com email já cadastrado! Insira outro email por favor...");

        this.redirectPage("/publico/cadastro-usuario.xhtml");

    }

    private void redirectPage(String page) {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + page);

        } catch (IOException io) {
            log.info("Redirecionando para a pagina: {}", page);
            log.error(io.getMessage());
        }
    }



    private boolean validarNomeEmail(StringBuilder stringBuilder) {

        if (Objects.isNull(usuarioForm.getNome()) || usuarioForm.getNome().isEmpty()) {
            stringBuilder.append("O nome do usuario deve ser preenchido!");
            return false;
        }
        if (Objects.isNull(usuarioForm.getEmail()) || usuarioForm.getEmail().isEmpty()) {
            stringBuilder.append("O email do usuario deve ser preenchido!");
            return false;
        } else {
            if (!Pattern.compile("^(.+)@(\\S+)$")
                    .matcher(usuarioForm.getEmail())
                    .matches()) {
                stringBuilder.append("O email possui um formato inválido!");
                return false;
            }
        }

        return true;
    }
}
