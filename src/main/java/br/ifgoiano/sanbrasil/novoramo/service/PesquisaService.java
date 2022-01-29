package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.repository.PesquisaRepository;
import br.ifgoiano.sanbrasil.novoramo.vo.QtdCasosDoencaEstadoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class PesquisaService implements ISanBrasilService<Pesquisa, Long> {

    @Autowired
    private PesquisaRepository pesquisaRepository;

    @Override
    public Pesquisa save(@Valid Pesquisa entity) {
        log.info("save em PesquisaService");
        return pesquisaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById em PesquisaService");
        pesquisaRepository.deleteById(id);
    }

    @Override
    public List<Pesquisa> findAll() {
        log.info("findAll em PesquisaService");
        return pesquisaRepository.findAll();
    }

    @Override
    public Pesquisa findById(Long id) {
        log.info("findById em PesquisaService");
        return pesquisaRepository.findById(id).orElse(null);
    }

    public Long  count() {
        log.info("count em PesquisaService");
        return pesquisaRepository.count();
    }


    public List<QtdCasosDoencaEstadoVO> findQtdCasosDoencaEstado(){
        log.info("findQtdCasosDoencaEstado em PesquisaService");
        return pesquisaRepository.findQtdCasosDoencaEstado();
    }
}
