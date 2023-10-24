package apicela.duplicata.repositories;

import apicela.duplicata.Models.Duplicata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplicataRepository extends JpaRepository<Duplicata, Long> {
}
