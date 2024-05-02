/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.services;

import jakarta.transaction.Transactional;
import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityService;
import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.exceptions.VeiculoNotFoundException;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service da entidade Veiculo
 */
@Service
public class VeiculoService extends AbstractEntityService<Veiculo, Long, VeiculoInputMapper, VeiculoOutputMapper> {
    private static final String VEICULO_NOT_FOUND_KEY = "exception.veiculo.not.found";
    private final VeiculoRepository veiculoRepository;

    private final VeiculoMapper veiculoMapper;

    private final MessageUtil messageUtil;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper, MessageUtil messageUtil) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
        this.messageUtil = messageUtil;
    }

    /**
     * Busca o veículo pelo id
     *
     * @param idVeiculo
     * @return Veiculo
     */
    public Veiculo findById(Long idVeiculo) {
        return veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new VeiculoNotFoundException(messageUtil.getMessage(VEICULO_NOT_FOUND_KEY)));
    }

    /**
     * Busca todos os veículos associados a um proprietário pelo seu ID.
     *
     * @param idProprietario O ID do proprietário para o qual os veículos serão recuperados.
     * @return Um conjunto (Set) de objetos VeiculoOutputMapper representando os veículos do proprietário.
     */
    public Set<VeiculoOutputMapper> findAllByIdProprietario(Long idProprietario) {
        // Recupera os veículos associados ao proprietário do repositório
        final Set<Veiculo> veiculosProprietario = veiculoRepository.findAllByProprietarioId(idProprietario);
        // Realiza o mapeamento dos veículos para objetos do tipo VeiculoOutputMapper usando o ModelMapper
        // O uso de TypeToken ajuda a lidar com a natureza genérica da coleção Set<VeiculoOutputMapper>
        return veiculoMapper.convertToSetDto(veiculosProprietario);
    }

    /**
     * Verifica se o veículo está marcado como principal, se estiver altera para false.
     * Se o veículo não for o principal, desmarca o veículo principal e marca o veículo atual como principal.
     *
     * @param veiculo
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateVeiculoPrincipal(VeiculoInputMapper veiculo) {
        if (veiculo.isPrincipal()) {
            var veiculoEncontrado = veiculoRepository.findById(veiculo.getId()).get();
            veiculoEncontrado.setPrincipal(false);
            veiculoRepository.save(veiculoEncontrado);
            return;
        }
        // Altera para false o veículo principal do proprietário
        veiculoRepository.updateVeiculoPrincipalFalse(veiculo.getIdProprietario());
        // Altera para true o veículo principal atual do proprietário
        veiculoRepository.updateVeiculoPrincipalTrue(veiculo.getId(), veiculo.getIdProprietario());
    }

    /**
     * Busca o veículo principal pelo id do proprietário
     *
     * @param idProprietario
     * @return Optional<VeiculoOutputMapper>
     */
    public Optional<VeiculoOutputMapper> findVeiculoPrincipal(Long idProprietario) {
        Optional<Veiculo> veiculoEncontrado = veiculoRepository.findByProprietarioIdAndPrincipal(idProprietario, true);
        if (veiculoEncontrado.isPresent()) {
            return veiculoEncontrado.map(veiculoMapper::convertToDto);
        }
        return Optional.empty();
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @Transactional(rollbackOn = Exception.class)
    public Set<Veiculo> saveAll(Set<Veiculo> veiculo) {
        return new HashSet<>(veiculoRepository.saveAll(veiculo));
    }
}
