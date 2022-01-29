package br.ifgoiano.sanbrasil.novoramo.repository;

import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.vo.QtdCasosDoencaEstadoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {


    @Query(value = "select new br.ifgoiano.sanbrasil.novoramo.vo.QtdCasosDoencaEstadoVO(sum(p.valor), uf.nome) " +
            "from Pesquisa p " +
            "inner join BrasilGrandeRegiaoUf uf on p.brasilGrandeRegialUf = uf " +
            "group by uf.nome " +
            "order by uf.nome")
    List<QtdCasosDoencaEstadoVO> findQtdCasosDoencaEstado();
}
