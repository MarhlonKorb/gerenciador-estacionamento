package marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.builder;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;

public class UsuarioBuilder implements IUsuarioBuilder {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private Status status;

    @Override
    public IUsuarioBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public UsuarioBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UsuarioBuilder setPassword(String password) {
        this.password = password;
        return this;
    }


    @Override
    public UsuarioBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public UsuarioBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Usuario build() {
        return new Usuario(id, email, password, role, status);
    }
}
