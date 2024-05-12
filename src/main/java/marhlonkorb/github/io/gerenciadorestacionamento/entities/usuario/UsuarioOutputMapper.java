package marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.ProprietarioOutputMapper;

/**
 * Output da entidade Usuario
 */
public class UsuarioOutputMapper {

    private Long id;
    private String email;
    private Status status;
    private Role role;

    private Proprietario proprietario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
