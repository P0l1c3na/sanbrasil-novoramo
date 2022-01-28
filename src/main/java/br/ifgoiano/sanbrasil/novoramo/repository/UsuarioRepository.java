package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("select a from Usuario a where a.nome like :nome")
    Usuario findByNome(String nome);

    @Modifying
    @Transactional
    @Query("update  Usuario u set u.senha = :senha where u.email = :email")
    void updateSenha(String senha, String email);
}
