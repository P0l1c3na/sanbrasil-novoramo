package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.builders.*;
import br.ifgoiano.sanbrasil.novoramo.dto.PnsbDTO;
import br.ifgoiano.sanbrasil.novoramo.model.Pesquisa;
import br.ifgoiano.sanbrasil.novoramo.repository.DoencaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class JsonImportService {

    @Value("classpath:data/pesquisanacionaldesaneamentobasicopnsb.json")
    private Resource resourceFile;

    @Autowired
    private PesquisaService pesquisaService;

    @Autowired
    private BrasilGrandeRegiaoUfService brasilGrandeRegialUfService;

    @Autowired
    private DoencaService doencaService;

    @Autowired
    private TerritorioService territorioService;

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @Autowired
    private VariavelService variavelService;

    public void lerArquivoJson() throws IOException {
        var objectMapper = new ObjectMapper();

        log.info("Importando os dados do arquivo: pesquisanacionaldesaneamentobasicopnsb.json");
        List<PnsbDTO> dadosPbsnn = objectMapper.readValue(resourceFile.getFile(), new TypeReference<List<PnsbDTO>>() {
        });

        log.info("Resultado da importação do arquivo: {}", dadosPbsnn);

        tratarDadosDoArquivo(dadosPbsnn);
    }

    private void tratarDadosDoArquivo(List<PnsbDTO> dadosPbsnn) {
        log.info("Tratando os dados obtidos do arquivo");
        var listaFiltrada = dadosPbsnn.subList(1, dadosPbsnn.size() - 1);

        listaFiltrada.forEach(pnsbDTO -> {
            var pesquisa = PesquisaBuilder.aPesquisa()
                    .withAno(Integer.parseInt(pnsbDTO.getAno()))
                    .withValor(getValor(pnsbDTO.getValor()))
                    .withDoenca(
                            DoencaBuilder.aDoenca()
                                    .withCodigo(Integer.parseInt(pnsbDTO.getCodigoTipoDoenca()))
                                    .withNome(pnsbDTO.getTipoDoenca())
                                    .build()
                    )
                    .withTerritorio(
                            TerritorioBuilder.aTerritorio()
                                    .withCodigo(Integer.parseInt(pnsbDTO.getCodigoNivelTerritorial()))
                                    .withNome(pnsbDTO.getNivelTerritorial())
                                    .build()
                    )
                    .withUnidadeMedida(
                            UnidadeMedidaBuilder.anUnidadeMedida()
                                    .withCodigo(Integer.parseInt(pnsbDTO.getCodigoUnidadeMedida()))
                                    .withNome(pnsbDTO.getUnidadeMedida())
                                    .build()
                    )
                    .withVariavel(
                            VariavelBuilder.aVariavel()
                                    .withCodigo(Integer.parseInt(pnsbDTO.getCodigoNivelTerritorial()))
                                    .withNome(pnsbDTO.getVariavel())
                                    .build()
                    )
                    .withBrasilGrandeRegialUf(
                            BrasilGrandeRegialUfBuilder.aBrasilGrandeRegialUf()
                                    .withCodigo(Integer.parseInt(pnsbDTO.getCodigoBrGrUf()))
                                    .withNome(pnsbDTO.getBrGrUf())
                                    .build()
                    )
                    .build();

            this.salvarPesquisa(pesquisa);
        });
    }

    private void salvarPesquisa(Pesquisa pesquisa) {
        log.info("Salvando os dados obtidos do arquivo");

        brasilGrandeRegialUfService.save(pesquisa.getBrasilGrandeRegialUf());
        territorioService.save(pesquisa.getTerritorio());
        doencaService.save(pesquisa.getDoenca());
        variavelService.save(pesquisa.getVariavel());
        unidadeMedidaService.save(pesquisa.getUnidadeMedida());

        pesquisaService.save(pesquisa);
        log.info("Pesquisa Salva: {}", pesquisa);

    }

    private Long getValor(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (Exception e) {
            return 0L;
        }
    }

}
