package apicela.duplicata.Services;

import apicela.duplicata.Exceptions.RodapeInvalido;
import apicela.duplicata.Models.Rodape;
import org.springframework.stereotype.Service;

@Service

public class RodapeService {

    Rodape validarRodape(String linha, int numberLine) throws RodapeInvalido {
        String espacoReservado = linha.substring(3, 80);
        if (!espacoReservado.matches("M{77}")) {
            throw new RodapeInvalido("Erro na linha " + numberLine + ": Espaço reservado preenchido incorretamente. Favor completar unicamente com 'M'");
        }
        return new Rodape("003", espacoReservado);
    }
}