package marhlonkorb.github.io.gerenciadorestacionamento.repositories;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository da entidade Vaga
 */
@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
