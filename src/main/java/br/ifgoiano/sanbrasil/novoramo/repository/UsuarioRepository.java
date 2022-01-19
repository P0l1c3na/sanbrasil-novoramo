package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("select a from Usuario a where a.nome like :nome")
    Usuario findByNome(String nome);
}
