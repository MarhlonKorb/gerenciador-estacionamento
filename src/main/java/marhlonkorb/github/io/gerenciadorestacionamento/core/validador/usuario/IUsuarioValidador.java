package marhlonkorb.github.io.gerenciadorestacionamento.core.validador.usuario;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.exceptions.UsuarioException;

/**
 * Interface que define contrato para as validações de usuário
 */
public interface IUsuarioValidador {
    public void validaUsuarioIsCadastrado(String email);
    public void validaUsuarioIsNotCadastrado(String email);

    public void contemPassword(String password) throws UsuarioException;

    public void validar(Usuario usuario);

}
