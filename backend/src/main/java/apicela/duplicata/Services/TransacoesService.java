package apicela.duplicata.Services;

import apicela.duplicata.Exceptions.TransacaoInvalida;
import apicela.duplicata.Models.Transacoes;
import apicela.duplicata.repositories.TransacoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacoesService {

    private final TransacoesRepository transacoesRepository;

    Transacoes validarTransacao(String linha, int numeroLinha) throws TransacaoInvalida {
        List<String> errors = new ArrayList<>();
        BigDecimal valorTransacao = null;
        char tipoTransacao = linha.charAt(3);
        if (tipoTransacao != 'C' && tipoTransacao != 'D' && tipoTransacao != 'T') {
            errors.add("Erro na linha " + numeroLinha + ": Tipo de transação inválido. ");
        }
        String stringValue = (linha.substring(4, 20).replaceAll("_", ""));
        if (!stringValue.matches("^[0-9]{0,16}$")) {
            errors.add("Erro na linha " + numeroLinha + ": Valor da transação não está no formato correto");
        } else {
            valorTransacao = new BigDecimal(stringValue).movePointLeft(2);
        }
        String contaOrigem = linha.substring(20, 36).replaceAll("_", "");
        if (!contaOrigem.matches("^[0-9]{0,16}$")) {
            errors.add("Erro na linha " + numeroLinha + ": Conta origem não está no formato correto");
        }
        String contaDestino = linha.substring(36, 52).replaceAll("_", "");
        if (!contaDestino.matches("^[0-9]{0,16}$")) {
            errors.add("Erro na linha " + numeroLinha + ": Conta destino não está no formato correto");
        }
        String espacoReservado = linha.substring(52, 80);

        if (!errors.isEmpty()) {
            String mensagemErro = String.join("\n", errors);
            log.info("Transação `{}` recusada. Erros: {}", linha, errors);
            throw new TransacaoInvalida(mensagemErro);
        }
        var transacao = new Transacoes(null, "002", tipoTransacao, valorTransacao, contaOrigem, contaDestino, espacoReservado);
        return transacoesRepository.save(transacao);
    }


}