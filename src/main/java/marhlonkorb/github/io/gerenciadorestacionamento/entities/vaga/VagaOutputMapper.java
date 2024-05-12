package marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.StatusVaga;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;

/**
 * Output da entidade Vaga
 */
public class VagaOutputMapper {

    private Long id;

    private Long idVeiculo;

    private Status status;

    private StatusVaga statusVaga;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusVaga getStatusVaga() {
        return statusVaga;
    }

    public void setStatusVaga(StatusVaga statusVaga) {
        this.statusVaga = statusVaga;
    }
}
