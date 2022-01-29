package br.ifgoiano.sanbrasil.novoramo.bean;

import br.ifgoiano.sanbrasil.novoramo.bean.form.RecuperacaoSenhaForm;
import br.ifgoiano.sanbrasil.novoramo.service.RecuperacaoSenhaService;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Component
@RequestScoped
@Slf4j
public class RecuperacaoSenhaBean implements Serializable {

    private final RecuperacaoSenhaForm recuperacaoSenhaForm;
    private final RecuperacaoSenhaService recuperacaoSenhaService;
    private final UsuarioService usuarioService;

    @Inject
    public RecuperacaoSenhaBean(RecuperacaoSenhaForm recuperacaoSenhaForm, RecuperacaoSenhaService recuperacaoSenhaService, UsuarioService usuarioService) {
        this.recuperacaoSenhaForm = recuperacaoSenhaForm;
        this.recuperacaoSenhaService = recuperacaoSenhaService;
        this.usuarioService = usuarioService;
    }

    public void solicitarRecuperacaoDeSenha() {

        if (Objects.nonNull(recuperacaoSenhaForm.getEmail()) && !recuperacaoSenhaForm.getEmail().isEmpty()) {
            log.info("Solicitação de recuperação de Senha");
            recuperacaoSenhaForm.setMensagem("");
            var sucesso = recuperacaoSenhaService.recuperacaoSenha(recuperacaoSenhaForm.getEmail());
            if (sucesso) {
                recuperacaoSenhaForm.setFalhou(Boolean.FALSE);
                recuperacaoSenhaForm.setEnviouCodigo(Boolean.TRUE);
                recuperacaoSenhaForm.setMensagem("Um Código de verificação será enviado para seu email para alteração de senha!");
            } else {
                recuperacaoSenhaForm.setFalhou(Boolean.TRUE);
                recuperacaoSenhaForm.setMensagem("Erro ao enviar a solicitação de recuperação de senha...\n Verifique seu email e tente novamente!");
            }

            this.redirectPage("/publico/recuperar-senha.xhtml");
        }

    }

    public void validarCodigo() {
        recuperacaoSenhaForm.setMensagem("");

        if (recuperacaoSenhaService.validarCodigo(recuperacaoSenhaForm.getCodigoRecuperacao(), recuperacaoSenhaForm.getEmail())) {
            recuperacaoSenhaForm.setFalhou(Boolean.FALSE);
            recuperacaoSenhaForm.setCodigoValido(Boolean.TRUE);
            log.info("Código de recuperação válido");
        } else {
            recuperacaoSenhaForm.setFalhou(Boolean.TRUE);
            recuperacaoSenhaForm.setCodigoValido(Boolean.FALSE);
            recuperacaoSenhaForm.setMensagem("Código Inválido! Por favor, realize uma nova solicitação");
        }
        this.redirectPage("/publico/recuperar-senha.xhtml");
    }

    public void alterarSenha() {
        recuperacaoSenhaForm.setMensagem("");
        var sb = new StringBuilder();
        if (!usuarioService.validarSenha(sb, recuperacaoSenhaForm.getSenha(), recuperacaoSenhaForm.getConfirmacaoSenha())) {
            recuperacaoSenhaForm.setMensagem(sb.toString());
            recuperacaoSenhaForm.setFalhou(Boolean.TRUE);
            log.info("Falha ao validar a senha do usuário: {} - {}", recuperacaoSenhaForm.getEmail(), sb.toString());
            this.redirectPage("/publico/recuperar-senha.xhtml");
        } else {
            log.info("Alterando a senha do usuário: {}", recuperacaoSenhaForm.getEmail());
            usuarioService.updateSenha(recuperacaoSenhaForm.getSenha(), recuperacaoSenhaForm.getEmail());
            recuperacaoSenhaService.inativarSolicitacoesDeRecuperacaoByUsuario(recuperacaoSenhaForm.getEmail());
            this.redirectPage("/login.xhtml?senhaRecuperada=true");
        }
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
}
