package marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.builder;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;

public interface IUsuarioBuilder {
    IUsuarioBuilder setId(Long id);

    IUsuarioBuilder setEmail(String email);

    IUsuarioBuilder setPassword(String password);

    IUsuarioBuilder setRole(Role role);

    IUsuarioBuilder setStatus(Status status);

    Usuario build();
}
