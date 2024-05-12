package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VinculaVeiculoVagaUseCase {


    private final VagaService vagaService;

    private final VeiculoService veiculoService;

    public VinculaVeiculoVagaUseCase(VagaService vagaService, VeiculoService veiculoService) {
        this.vagaService = vagaService;
        this.veiculoService = veiculoService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long idVeiculo, Long idVaga) {
        final Veiculo veiculoEncontrado = veiculoService.findById(idVeiculo);
        final Vaga vagaEncontrada = vagaService.findById(idVaga);
        if (!veiculoEncontrado.isContemVaga() && !vagaEncontrada.isContemVeiculo()) {
            veiculoEncontrado.adicionarVaga(vagaEncontrada);
            vagaEncontrada.adicionarVeiculo(veiculoEncontrado);
            vagaService.save(vagaEncontrada);
        }
    }
}
