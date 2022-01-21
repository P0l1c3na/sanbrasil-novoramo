package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Variavel;
import br.ifgoiano.sanbrasil.novoramo.repository.VariavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class VariavelService implements ISanBrasilService<Variavel, Integer> {

    @Autowired
    private VariavelRepository variavelRepository;

    @Override
    public Variavel save(@Valid Variavel entity) {
        return variavelRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        variavelRepository.deleteById(id);
    }

    @Override
    public List<Variavel> findAll() {
        return variavelRepository.findAll();
    }

    @Override
    public Variavel findById(Integer id) {
        return variavelRepository.findById(id).orElse(null);
    }
}
