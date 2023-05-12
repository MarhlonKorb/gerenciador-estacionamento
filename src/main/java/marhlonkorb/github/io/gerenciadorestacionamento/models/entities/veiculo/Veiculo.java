package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.abstractentities.entidadecomid.EntidadeComId;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.pessoa.Pessoa;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;

@Entity(name = VeiculoDbConstantes.TABLE_NAME)
public class Veiculo extends EntidadeComId {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = VeiculoDbConstantes.PESSOA_ID)
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.EAGER)
    private Vaga vaga;

    @NotBlank(message = "A placa do veículo deve ser informada")
    private String placa;

    @NotBlank(message = "A marca do veículo é obrigatória informar")
    private String marca;

    @NotBlank(message = "O modelo do veículo é obrigatório informar")
    private String modelo;

    @JsonFormat(pattern = VeiculoDbConstantes.ANO_PATTERN)
    private Date ano;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }
}
