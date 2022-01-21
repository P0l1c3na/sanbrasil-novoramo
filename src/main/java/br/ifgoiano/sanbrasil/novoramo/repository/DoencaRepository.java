package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoencaRepository extends JpaRepository<Doenca, Integer> {
}
