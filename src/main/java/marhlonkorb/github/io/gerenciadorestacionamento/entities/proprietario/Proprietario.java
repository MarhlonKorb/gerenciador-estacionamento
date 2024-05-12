package marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid.EntidadeComId;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.TipoPessoa;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
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
    @OneToOne
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = ProprietarioDbConstantes.USUARIO_ID)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = ProprietarioDbConstantes.PROPRIETARIO_ID)
    private Set<Veiculo> veiculos;

    @NotNull
    private String nome;

    @Column
    private String cpfCnpj;

    @Column
    private String apartamento;

    @Column(name = ProprietarioDbConstantes.DATA_NASCIMENTO)
    @JsonFormat(pattern = ProprietarioDbConstantes.DATA_NASCIMENTO_PATTERN)
    private String dataNascimento;

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
