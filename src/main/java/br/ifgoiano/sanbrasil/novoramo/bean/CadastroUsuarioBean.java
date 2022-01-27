package br.ifgoiano.sanbrasil.novoramo.bean;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.form.UsuarioForm;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;

@Component
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

    private final UsuarioForm usuarioForm;
    private final UsuarioService usuarioService;

    @Inject
    public CadastroUsuarioBean(@Autowired UsuarioForm usuarioForm, @Autowired UsuarioService usuarioService) {
        this.usuarioForm = usuarioForm;
        this.usuarioService = usuarioService;
    }

    public String cadastrarUsuario() {
        var usuarioCadastrado = usuarioService.findById(usuarioForm.getEmail());
        if (Objects.isNull(usuarioCadastrado)) {


            var usuario = usuarioService.createUser(usuarioForm.getEmail(),
                    usuarioForm.getSenha(),
                    Collections.singletonList(new Perfil(2L, PerfilUsuario.USUARIO.name())),
                    Boolean.TRUE,
                    usuarioForm.getNome(),
                    usuarioForm.getTelefone());

            if (Objects.nonNull(usuario)) {
                usuarioForm.setSucesso(Boolean.TRUE);
                return "login.xhtml";
            } else
                usuarioForm.setSucesso(Boolean.FALSE);
        }
        usuarioForm.setMensagem("Usuario com email já cadastrado! Insira outro email por favor...");

        return "/publico/cadastro-usuario.xhtml";
    }
}
