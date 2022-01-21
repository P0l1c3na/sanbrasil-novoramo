package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {
}
