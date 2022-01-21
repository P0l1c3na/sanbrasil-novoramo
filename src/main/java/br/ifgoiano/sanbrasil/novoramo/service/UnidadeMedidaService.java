package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.UnidadeMedida;
import br.ifgoiano.sanbrasil.novoramo.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UnidadeMedidaService implements ISanBrasilService<UnidadeMedida, Integer> {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @Override
    public UnidadeMedida save(@Valid UnidadeMedida entity) {
        return unidadeMedidaRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        unidadeMedidaRepository.deleteById(id);
    }

    @Override
    public List<UnidadeMedida> findAll() {
        return unidadeMedidaRepository.findAll();
    }

    @Override
    public UnidadeMedida findById(Integer id) {
        return unidadeMedidaRepository.findById(id).orElse(null);
    }
}
