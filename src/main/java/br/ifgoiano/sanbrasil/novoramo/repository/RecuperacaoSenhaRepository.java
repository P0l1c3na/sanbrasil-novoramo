package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, Long> {

    @Query("From RecuperacaoSenha r where r.usuario.email = :email and r.realizada = false")
    RecuperacaoSenha findByEmailUsuario(String email);

    @Modifying
    @Transactional
    @Query("update RecuperacaoSenha r set r.realizada = true where r.usuario.email = :email")
    void inativarSolicitacoesDeRecuperacaoByUsuario(String email);
}
