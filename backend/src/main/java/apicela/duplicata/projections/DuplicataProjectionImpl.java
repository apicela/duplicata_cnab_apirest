package apicela.duplicata.projections;

import apicela.duplicata.Models.Transacoes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DuplicataProjectionImpl implements DuplicataProjection {
    private final long duplicata_id;
    private final String razaoSocial;
    private final String identificadorEmpresa;
    private final List<Transacoes> transacoes;
}
