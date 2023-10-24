package apicela.duplicata.repositories;

import apicela.duplicata.Models.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
