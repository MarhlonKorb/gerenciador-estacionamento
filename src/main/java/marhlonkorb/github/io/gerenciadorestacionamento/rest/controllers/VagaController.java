/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityController;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.services.VinculaVeiculoVagaUseCase;
import org.springframework.web.bind.annotation.*;

/**
 * Classe responsável por controlar o registro de veiculos no estacionamento
 */
@RestController
@RequestMapping("vaga")
public class VagaController extends AbstractEntityController<Vaga, Long, VagaInputMapper, VagaOutputMapper> {

    private final VinculaVeiculoVagaUseCase vinculaVeiculoVagaUseCase;

    public VagaController(VinculaVeiculoVagaUseCase vinculaVeiculoVagaUseCase) {
        this.vinculaVeiculoVagaUseCase = vinculaVeiculoVagaUseCase;
    }

    @PutMapping("/vinculaVeiculoVaga/idVeiculo={idVeiculo}&idVaga={idVaga}")
    public void executaVinculoVeiculoVaga(@PathVariable Long idVeiculo, @PathVariable Long idVaga) {
        vinculaVeiculoVagaUseCase.execute(idVeiculo, idVaga);
    }

}
