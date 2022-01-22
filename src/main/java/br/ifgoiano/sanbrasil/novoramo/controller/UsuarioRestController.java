package br.ifgoiano.sanbrasil.novoramo.controller;

import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilRestController;
import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController implements ISanBrasilRestController<Usuario, String> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario entity) {
        return ResponseEntity.ok(usuarioService.save(entity));
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        usuarioService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping("/salvar-externo")
    public ResponseEntity<Usuario> salvarExterno(@RequestBody Usuario entity) {
        return ResponseEntity.ok(usuarioService.salvarExterno(entity));
    }
}
