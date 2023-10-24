package apicela.duplicata.Services;

import apicela.duplicata.Exceptions.DuplicataInvalida;
import apicela.duplicata.Models.Cabecalho;
import apicela.duplicata.Models.Duplicata;
import apicela.duplicata.Models.Rodape;
import apicela.duplicata.Models.Transacoes;
import apicela.duplicata.projections.DuplicataProjectionImpl;
import apicela.duplicata.repositories.DuplicataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DuplicataService {

    final CabecalhoService cabecalhoService;
    final TransacoesService transacoesService;
    final RodapeService rodapeService;
    final DuplicataRepository duplicataRepository;

    @Transactional
    public Duplicata validarDuplicata(List<String> linhasArquivo, String nomeArquivo) throws Exception {
        List<String> errors = new ArrayList<>();
        var cabecalho = new Cabecalho();
        ArrayList<Transacoes> list = new ArrayList<Transacoes>();
        Rodape rodape = new Rodape();
        int numberLine = 1;
        boolean lastLine = false;
        boolean hasRodape = false;

        for (String linha : linhasArquivo) {
            try {
                if (linha.length() != 80) {
                    throw new StringIndexOutOfBoundsException("Erro na linha " + numberLine + ": A linha possui " + linha.length() + " caracteres, a quantidade esperada é de: 80 caracteres.");
                } else if (numberLine == 1) {
                    cabecalho = cabecalhoService.validarCabecalho(linha, numberLine);
                    numberLine++;
                } else if (lastLine) {
                    throw new DuplicataInvalida("Erro na linha " + numberLine + ": Está fora dos padrões CNAB, não deverá conter mais linhas após rodapé.");
                } else if (linha.startsWith("002")) {
                    list.add(transacoesService.validarTransacao(linha, numberLine));
                    numberLine++;
                } else if (linha.startsWith("001")) {
                    throw new DuplicataInvalida("Erro na linha " + numberLine + ": Somente é permitido um único cabeçalho por duplicata. Identificação do tipo de registro inválido.");
                } else if (linha.startsWith("003")) {
                    lastLine = true;
                    hasRodape = true;
                    rodape = rodapeService.validarRodape(linha, numberLine);
                    numberLine++;
                } else {
                    throw new DuplicataInvalida("Erro na linha " + numberLine + ": Identificação do tipo de registro inválido.");
                }
            } catch (Exception ex) {
                errors.add(ex.getMessage());
                numberLine++;
            }
        }

        if (!hasRodape) {
            errors.add("Duplicata inválida. Necessita de um rodapé válido.");
        }

        if (!errors.isEmpty()) {
            String mensagemErro = String.join("\n", errors);
            log.info("Duplicata {} recusada. Erros:", errors);
            throw new DuplicataInvalida(mensagemErro);
        }
        var duplicata = new Duplicata();
        duplicata.setCabecalho(cabecalho);
        duplicata.setTransacoes(list);
        duplicata.setRodape(rodape);
        return duplicataRepository.save(duplicata);
    }


    public Optional<DuplicataProjectionImpl> findById(@PathVariable(value = "id") Long id) throws IllegalAccessException {
        Optional<Duplicata> optional = duplicataRepository.findById(id);
        if (optional.isPresent()) {
            var duplicata = optional.get();
            return Optional.of(duplicata).map(
                    it -> {
                        return new DuplicataProjectionImpl(
                                duplicata.getId(), duplicata.getCabecalho().getRazaoSocialEmpresa(),
                                duplicata.getCabecalho().getIdentificadorEmpresa(), duplicata.getTransacoes()
                        );
                    });
        }
        throw new IllegalAccessException("Duplicata de id: " + id + " não encontrada.");
    }

    public List<DuplicataProjectionImpl> findAll() {
        List<Duplicata> lista = duplicataRepository.findAll();
        return lista.stream().map(
                duplicata -> {
                    return new DuplicataProjectionImpl(
                            duplicata.getId(), duplicata.getCabecalho().getRazaoSocialEmpresa(),
                            duplicata.getCabecalho().getIdentificadorEmpresa(), duplicata.getTransacoes()
                    );
                }).collect(Collectors.toList());
    }
}


