package marhlonkorb.github.io.gerenciadorestacionamento.validador.email;

import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;

/**
 * Interface que define contrato para as validações de email
 */
public interface IEmailValidador {

    void validar(String email);
}
