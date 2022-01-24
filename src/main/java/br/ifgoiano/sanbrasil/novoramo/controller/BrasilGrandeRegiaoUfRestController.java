package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.model.BrasilGrandeRegiaoUf;
import br.ifgoiano.sanbrasil.novoramo.service.BrasilGrandeRegiaoUfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publico/brasil-grande-regiao-uf")
@Slf4j
public class BrasilGrandeRegiaoUfRestController {

    @Autowired
    private BrasilGrandeRegiaoUfService brasilGrandeRegiaoUfService;

    @GetMapping
    public List<BrasilGrandeRegiaoUf> findAll(){
        log.info("findAll PUBLICO em BrasilGrandeRegiaoUfController ");
        return brasilGrandeRegiaoUfService.findAll();
    }

    @GetMapping("/{id}")
    public BrasilGrandeRegiaoUf findById(@PathVariable Integer id){
        log.info("findById PUBLICO em BrasilGrandeRegiaoUfController ");
        return brasilGrandeRegiaoUfService.findById(id);
    }

}
