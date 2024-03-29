package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid.EntidadeComId;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.TipoPessoa;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Set;

/**
 * Entidade Proprietario
 */
@Entity
@Table(name = ProprietarioDbConstantes.TABLE_NAME)
public class Proprietario extends EntidadeComId {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProprietarioDbConstantes.USUARIO_ID)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo")
    private Set<Veiculo> veiculos;

    @NotNull
    private String nome;

    @Column
    private String cpfCnpj;

    @Column
    private String apartamento;

    @Column(name = ProprietarioDbConstantes.DATA_NASCIMENTO)
    @JsonFormat(pattern = ProprietarioDbConstantes.DATA_NASCIMENTO_PATTERN)
    private LocalDate dataNascimento;

    @Column
    private String telefone;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Set<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Set<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
