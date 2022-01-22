package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PesquisaService implements ISanBrasilService<Pesquisa, Long> {

    @Autowired
    private PesquisaRepository pesquisaRepository;

    @Override
    public Pesquisa save(@Valid Pesquisa entity) {
        return pesquisaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        pesquisaRepository.deleteById(id);
    }

    @Override
    public List<Pesquisa> findAll() {
        return pesquisaRepository.findAll();
    }

    @Override
    public Pesquisa findById(Long id) {
        return pesquisaRepository.findById(id).orElse(null);
    }

    public Long  count() {
        return pesquisaRepository.count();
    }


}
