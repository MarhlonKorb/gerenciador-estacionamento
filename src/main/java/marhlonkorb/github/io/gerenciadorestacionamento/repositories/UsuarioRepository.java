package marhlonkorb.github.io.gerenciadorestacionamento.repositories;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Repository da entidade Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    @Query(value = "SELECT * FROM usuario u WHERE u.email = ?1", nativeQuery = true)
    Usuario findUsuarioByEmail(String email);
}
