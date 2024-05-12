package marhlonkorb.github.io.gerenciadorestacionamento.repositories;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository da entidade Proprietario
 */
@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Optional<Proprietario> getByUsuarioId(Long idUsuario);
}
