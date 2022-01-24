package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.model.Doenca;
import br.ifgoiano.sanbrasil.novoramo.service.DoencaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publico/doenca")
@Slf4j
public class DoencaRestController {

    @Autowired
    private DoencaService doencaService;

    @GetMapping
    public List<Doenca> findAll(){
        log.info("findAll PUBLICO em DoencaController ");
        return doencaService.findAll();
    }

    @GetMapping("/{id}")
    public Doenca findById(@PathVariable Integer id){
        log.info("findById PUBLICO em DoencaController ");
        return doencaService.findById(id);
    }
}
