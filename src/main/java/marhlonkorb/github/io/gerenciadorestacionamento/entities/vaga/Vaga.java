package marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadeauditada.EntidadeAuditada;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo.Veiculo;

/**
 * Entidade Vaga
 */
@Entity(name = VagaDbConstantes.TABLE_NAME)
public class Vaga extends EntidadeAuditada {

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = VagaDbConstantes.VEICULO_ID)
    private Veiculo veiculo;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "status_vaga")
    @Enumerated(EnumType.STRING)
    private StatusVaga statusVaga;

    public Vaga() {
    }

    public Vaga(StatusVaga statusVaga) {
        this.statusVaga = statusVaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
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

    public boolean isContemVeiculo() {
        return this.getVeiculo() != null || this.statusVaga.equals(StatusVaga.O);
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.statusVaga = StatusVaga.O;
    }
}
