/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityController;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.rest.exception.ApiErrors;
import marhlonkorb.github.io.gerenciadorestacionamento.services.VinculaVeiculoVagaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> executaVinculoVeiculoVaga(@PathVariable Long idVeiculo, @PathVariable Long idVaga) throws Exception {
        try {
            vinculaVeiculoVagaUseCase.execute(idVeiculo, idVaga);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrors(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
        return ResponseEntity.ok().build();
    }

}
