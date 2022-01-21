package br.ifgoiano.sanbrasil.novoramo.config;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.service.JsonImportService;
import br.ifgoiano.sanbrasil.novoramo.service.PerfilService;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class UserDataInitialConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    JsonImportService jsonImportService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (usuarioService.count() == 0) {
            var funcaoAdm = perfilService.save(new Perfil(1L, PerfilUsuario.ADMIN.name()));
            perfilService.save(new Perfil(2L, PerfilUsuario.PRIVADO.name()));
            perfilService.save(new Perfil(3L, PerfilUsuario.PUBLICO.name()));
            usuarioService.createUser("phaelpolicena@gmail.com",
                    "senha123", Collections.singletonList(funcaoAdm), Boolean.TRUE, "Raphael Policena", "");

        }

        try {
            jsonImportService.lerArquivoJson();
        } catch (Exception e) {
            log.error("Erro ", e);
        }
    }

}
