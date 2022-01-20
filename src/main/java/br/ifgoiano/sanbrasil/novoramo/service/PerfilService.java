package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.interfaces.IService;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PerfilService implements IService<Perfil, Long> {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public Perfil save(@Valid Perfil entity) {
        return perfilRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        perfilRepository.deleteById(id);
    }

    @Override
    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public Perfil findById(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }
}
