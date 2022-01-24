package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.service.PesquisaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publico/pesquisa")
@Slf4j
public class PesquisaRestController {

    @Autowired
    private PesquisaService pesquisaService;


    @GetMapping
    public List<Pesquisa> findAll() {
        log.info("findAll PUBLICO em PesquisaController ");
        return pesquisaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pesquisa> findById(@PathVariable Long id) {
        log.info("findById PUBLICO em PesquisaController ");
        return ResponseEntity.ok(pesquisaService.findById(id));
    }
}
