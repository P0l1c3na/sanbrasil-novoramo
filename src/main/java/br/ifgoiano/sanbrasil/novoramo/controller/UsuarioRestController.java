package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publico/usuario")
@Slf4j
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvar-externo")
    public ResponseEntity<Usuario> salvarExterno(@RequestBody Usuario entity) {
        log.info("salvarExterno PUBLICO em UsuarioRestController: {} ", entity);
        return ResponseEntity.ok(usuarioService.salvarExterno(entity));
    }
}
