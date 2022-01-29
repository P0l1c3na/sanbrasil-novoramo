package br.ifgoiano.sanbrasil.novoramo.bean;

import br.ifgoiano.sanbrasil.novoramo.bean.to.DoencaTO;
import br.ifgoiano.sanbrasil.novoramo.service.DoencaService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScope
@Component
public class PrincipalBean implements Serializable {

    private final DoencaService doencaService;

    private final DoencaTO doencaTO;

    @Inject
    public PrincipalBean(DoencaService doencaService) {
        this.doencaService = doencaService;
        this.doencaTO = new DoencaTO();
    }

    @PostConstruct
    public void listarDoencas() {
        this.doencaTO.setListaDoencas(DoencaTO.toListaDoencaTO(doencaService.findAll()));
    }

    public List<DoencaTO> getListaDoencas() {
        return doencaTO.getListaDoencas();
    }

}
