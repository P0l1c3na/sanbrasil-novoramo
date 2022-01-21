package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Territorio;
import br.ifgoiano.sanbrasil.novoramo.repository.TerritorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class TerritorioService implements ISanBrasilService<Territorio, Integer> {

    @Autowired
    private TerritorioRepository territorioService;

    @Override
    public Territorio save(@Valid Territorio entity) {
        return territorioService.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        territorioService.deleteById(id);
    }

    @Override
    public List<Territorio> findAll() {
        return territorioService.findAll();
    }

    @Override
    public Territorio findById(Integer id) {
        return territorioService.findById(id).orElse(null);
    }
}
