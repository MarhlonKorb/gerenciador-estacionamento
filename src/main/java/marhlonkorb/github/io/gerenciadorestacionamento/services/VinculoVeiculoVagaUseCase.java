package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VinculoVeiculoVagaUseCase {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private VeiculoService veiculoService;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long idVeiculo, Long idVaga) {
        final Veiculo veiculoEncontrado = veiculoService.getVeiculoById(idVeiculo);
        final Vaga vagaEncontrada = vagaService.getVagaById(idVaga);
        if (!veiculoEncontrado.isContemVaga() && !vagaEncontrada.isContemVeiculo()) {
            veiculoEncontrado.adicionarVaga(vagaEncontrada);
            vagaEncontrada.adicionarVeiculo(veiculoEncontrado);
            veiculoService.save(veiculoEncontrado);
            vagaService.save(vagaEncontrada);
        }
    }
}
