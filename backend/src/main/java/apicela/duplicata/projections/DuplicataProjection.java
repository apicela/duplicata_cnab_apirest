package apicela.duplicata.projections;

import apicela.duplicata.Models.Transacoes;

import java.util.List;

public interface DuplicataProjection {
    long getDuplicata_id();

    String getRazaoSocial();

    String getIdentificadorEmpresa();

    List<Transacoes> getTransacoes();
}
