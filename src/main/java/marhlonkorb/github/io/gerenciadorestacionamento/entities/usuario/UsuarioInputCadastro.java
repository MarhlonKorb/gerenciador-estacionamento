package marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;

public record UsuarioInputCadastro(String email, String password, Role role) {
}
