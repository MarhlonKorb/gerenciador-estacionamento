package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;

/**
 * Interface que define contrato para as validações de usuário
 */
public interface IUsuarioValidador {

    public void validaIsUsuarioExistente(String email) throws UsuarioException;
    public void validaIsUsuarioInexistente(String email) throws UsuarioException;

    public void contemPassword(String password) throws UsuarioException;

    public void validar(Usuario usuario);
}
