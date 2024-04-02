package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoOutputMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Output da entidade Proprietario teste jenkins
 */
public class ProprietarioOutputMapper {

    private Long id;
    private Long idUsuario;
    private String nome;
    private String cpfCnpj;
    private String apartamento;

    private Set<VeiculoOutputMapper> veiculos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String telefone;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<VeiculoOutputMapper> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Set<VeiculoOutputMapper> veiculos) {
        this.veiculos = veiculos;
    }
}
