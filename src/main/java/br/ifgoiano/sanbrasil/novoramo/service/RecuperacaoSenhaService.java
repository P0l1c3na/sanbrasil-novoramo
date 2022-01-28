package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.RecuperacaoSenha;
import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.repository.RecuperacaoSenhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class RecuperacaoSenhaService implements ISanBrasilService<RecuperacaoSenha, Long> {

    @Autowired
    private RecuperacaoSenhaRepository recuperacaoSenhaRepository;

    @Autowired
    private EmailService emailService;

    public RecuperacaoSenha findByEmailUsuario(String email) {
        return recuperacaoSenhaRepository.findByEmailUsuario(email);
    }

    @Override
    public RecuperacaoSenha save(@Valid RecuperacaoSenha entity) {
        return recuperacaoSenhaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        recuperacaoSenhaRepository.deleteById(id);
    }

    @Override
    public List<RecuperacaoSenha> findAll() {
        return recuperacaoSenhaRepository.findAll();
    }

    @Override
    public RecuperacaoSenha findById(Long id) {
        return recuperacaoSenhaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Boolean recuperacaoSenha(String email) {
        try {


            log.info("Iniciada a recuperação de senha com email: {}", email);
            log.info("Inativando solicitações pendentes");
            this.inativarSolicitacoesDeRecuperacaoByUsuario(email);

            log.info("Gerando código de recuperação para o usuário {}", email);

            var recuperacao = new RecuperacaoSenha();
            var codigo = (int) Math.floor(100000 + Math.random() * 900000);
            recuperacao.setCodigoVerificacao(codigo);
            recuperacao.setRealizada(Boolean.FALSE);
            recuperacao.setUsuario(new Usuario(email));
            recuperacao.setDataHoraSolicitacao(LocalDateTime.now());
            this.save(recuperacao);

            log.info("Recuperação de Senha com os dados salvos aguardando usuário com email: {}", email);

            emailService.sendEmail(email, "Recuperação de Senha SanBrasil", "Codigo de recuperação: " + codigo + " - com validade de 5 minutos!");

            return true;
        } catch (Exception e) {
            log.error("Erro ao solicitar a recuperação de senha...", e);
            return false;
        }
    }

    public void inativarSolicitacoesDeRecuperacaoByUsuario(String email) {
        recuperacaoSenhaRepository.inativarSolicitacoesDeRecuperacaoByUsuario(email);
    }

    public Boolean validarCodigo(Integer codigo, String email) {
        log.info("Validando o código de recuperação do usuário: {}", email);
        var recuperacao = this.findByEmailUsuario(email);
        return ChronoUnit.MINUTES.between(recuperacao.getDataHoraSolicitacao(), LocalDateTime.now()) <= 5 && codigo.equals(recuperacao.getCodigoVerificacao());
    }

}
