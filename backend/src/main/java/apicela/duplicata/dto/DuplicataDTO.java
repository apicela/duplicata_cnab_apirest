package apicela.duplicata.dto;

import apicela.duplicata.Models.Cabecalho;
import apicela.duplicata.Models.Rodape;
import apicela.duplicata.Models.Transacoes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class DuplicataDTO {
    private Cabecalho cabecalho;
    private ArrayList<Transacoes> transacoes;
    private Rodape rodape;

}
