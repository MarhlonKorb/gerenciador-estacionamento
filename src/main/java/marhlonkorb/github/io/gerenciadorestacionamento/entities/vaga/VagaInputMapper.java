package marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;

import javax.swing.*;
import java.util.Map;

/**
 * Input da entidade Vaga
 */
public class VagaInputMapper {

    private Long id;

    private Long idVeiculo;

    private Integer numeroVaga;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Integer getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(Integer numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

}
