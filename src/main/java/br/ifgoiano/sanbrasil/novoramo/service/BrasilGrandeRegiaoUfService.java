package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.BrasilGrandeRegiaoUf;
import br.ifgoiano.sanbrasil.novoramo.repository.BrasilGrandeRegiaoUfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class BrasilGrandeRegiaoUfService implements ISanBrasilService<BrasilGrandeRegiaoUf, Integer> {

    @Autowired
    BrasilGrandeRegiaoUfRepository regialUfRepository;

    @Override
    public BrasilGrandeRegiaoUf save(@Valid BrasilGrandeRegiaoUf entity) {
        return regialUfRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        regialUfRepository.deleteById(id);
    }

    @Override
    public List<BrasilGrandeRegiaoUf> findAll() {
        return regialUfRepository.findAll();
    }

    @Override
    public BrasilGrandeRegiaoUf findById(Integer id) {
        return regialUfRepository.findById(id).orElse(null);
    }
}
