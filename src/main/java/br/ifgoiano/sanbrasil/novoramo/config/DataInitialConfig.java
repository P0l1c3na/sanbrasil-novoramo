package br.ifgoiano.sanbrasil.novoramo.config;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.service.JsonImportService;
import br.ifgoiano.sanbrasil.novoramo.service.PerfilService;
import br.ifgoiano.sanbrasil.novoramo.service.PesquisaService;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class DataInitialConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    PesquisaService pesquisaService;

    @Autowired
    JsonImportService jsonImportService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (usuarioService.count() == 0) {
            var funcaoAdm = perfilService.save(new Perfil(1L, PerfilUsuario.ADMIN.name()));
            perfilService.save(new Perfil(2L, PerfilUsuario.USUARIO.name()));
            perfilService.save(new Perfil(3L, PerfilUsuario.PUBLICO.name()));
            usuarioService.createUser("phaelpolicena@gmail.com",
                    "senha123", Collections.singletonList(funcaoAdm), Boolean.TRUE, "Raphael Policena", "");

        }

        try {
            if (pesquisaService.count() == 0)
                jsonImportService.lerArquivoJson();
        } catch (Exception e) {
            log.error("Erro ao ler o arquivo JSON: ", e);
        }
    }

}
