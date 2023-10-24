package apicela.duplicata.repositories;

import apicela.duplicata.Models.Cabecalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabecalhoRepository extends JpaRepository<Cabecalho, Long> {
}