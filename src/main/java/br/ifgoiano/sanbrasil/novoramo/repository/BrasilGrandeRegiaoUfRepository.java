package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.BrasilGrandeRegiaoUf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrasilGrandeRegiaoUfRepository extends JpaRepository<BrasilGrandeRegiaoUf, Integer> {
}
