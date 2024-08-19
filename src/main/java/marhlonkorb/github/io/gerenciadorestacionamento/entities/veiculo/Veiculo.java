package marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid.EntidadeComId;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga.StatusVaga;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga.Vaga;

/**
 * Entidade Veiculo
 */
@Entity(name = VeiculoDbConstantes.TABLE_NAME)
public class Veiculo extends EntidadeComId {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = VeiculoDbConstantes.PROPRIETARIO_ID)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Proprietario proprietario;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;

    @NotBlank
    private String placa;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    private String ano;

    @Column()
    private boolean principal;

    public Veiculo() {
    }

    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isContemVaga() {
        return this.getVaga() != null;
    }

    public void adicionarVaga(Vaga vaga) {
        vaga.setStatusVaga(StatusVaga.O);
        this.vaga = vaga;
    }
}
