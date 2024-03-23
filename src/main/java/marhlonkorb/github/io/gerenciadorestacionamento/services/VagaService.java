/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityService;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VagaRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

/**
 * Service da entidade Vaga
 */
@Service
public class VagaService extends AbstractEntityService<Vaga, Long, VagaInputMapper, VagaOutputMapper> {
    private final VagaRepository vagaRepository;

    public VagaService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    /**
     * Busca o proprietário pelo id
     *
     * @param idVaga
     * @return Proprietario
     */
    public Vaga getVagaById(Long idVaga) {
        return vagaRepository.findById(idVaga)
                .orElseThrow(() -> new InvalidDataAccessApiUsageException("Vaga não encontrada."));
    }

    public Vaga save(Vaga vaga){
        return vagaRepository.save(vaga);
    }
}
