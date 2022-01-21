package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Doenca;
import br.ifgoiano.sanbrasil.novoramo.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class DoencaService implements ISanBrasilService<Doenca, Integer> {

    @Autowired
    private DoencaRepository doencaRepository;

    @Override
    public Doenca save(@Valid Doenca entity) {
        return doencaRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        doencaRepository.deleteById(id);
    }

    @Override
    public List<Doenca> findAll() {
        return doencaRepository.findAll();
    }

    @Override
    public Doenca findById(Integer id) {
        return doencaRepository.findById(id).orElse(null);
    }
}
