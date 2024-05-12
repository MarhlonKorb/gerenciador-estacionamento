package marhlonkorb.github.io.gerenciadorestacionamento.repositories;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository da entidade Veiculo
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    /**
     * Busca todos os veículos de acordo com o idProprietario
     *
     * @param idProprietario dos veículos
     * @return Set<Veiculo> Veículos do proprietário
     */
    Set<Veiculo> findAllByProprietarioId(Long idProprietario);

    /**
     * Desmarca o veículo principal atual
     * @param idProprietario
     */
    @Modifying
    @Query("UPDATE veiculo v SET v.principal = false WHERE v.principal = true AND v.proprietario.id = :idProprietario")
    void updateVeiculoPrincipalFalse(@Param("idProprietario") Long idProprietario);

    @Modifying
    @Query("UPDATE veiculo v SET v.principal = true WHERE v.id = :idVeiculo AND v.proprietario.id = :idProprietario")
    void updateVeiculoPrincipalTrue(@Param("idVeiculo") Long idVeiculo, @Param("idProprietario") Long idProprietario);


    Optional<Veiculo> findByProprietarioIdAndPrincipal(Long idProprietario, boolean principal);
}
