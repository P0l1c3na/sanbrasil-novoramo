package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilRestController;
import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pesquisa")
public class PesquisaController {

    @Autowired
    private PesquisaService pesquisaService;


    @GetMapping
    public List<Pesquisa> findAll() {
        return pesquisaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pesquisa> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pesquisaService.findById(id));
    }
}
