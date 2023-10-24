package apicela.duplicata.Services;

import apicela.duplicata.Exceptions.CabecalhoInvalido;
import apicela.duplicata.Models.Cabecalho;
import apicela.duplicata.repositories.CabecalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CabecalhoService {
    private final CabecalhoRepository cabecalhoRepository;

    public Cabecalho validarCabecalho(String linha, int numberLine) throws CabecalhoInvalido {
        if (!(linha.startsWith("001"))) {
            throw new CabecalhoInvalido("Erro na linha 1 do arquivo. Uma duplicata CNAB, deve começar com o cabeçalho, que possui código 001.");
        }
        String razaoSocial = linha.substring(3, 33).replaceAll("_", "");
        String identificadorEmpresa = linha.substring(33, 47).replaceAll("_", "");
        String espacoReservado = linha.substring(47, 80);
        if (!espacoReservado.matches("M{33}")) {
            throw new CabecalhoInvalido("Erro na linha " + numberLine + ": Espaço reservado preenchido incorretamente. Favor completar unicamente com 'M'");
        }

        var cabecalho = new Cabecalho(null, "001", razaoSocial, identificadorEmpresa, espacoReservado);
        return cabecalhoRepository.save(cabecalho);
    }


}