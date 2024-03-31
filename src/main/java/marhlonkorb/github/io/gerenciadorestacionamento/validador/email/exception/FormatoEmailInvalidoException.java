package marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception;

/**
 * Exception para formato de email inválido
 */
public class FormatoEmailInvalidoException extends RuntimeException {
    public FormatoEmailInvalidoException(String mensagem) {
        super(mensagem);
    }
}
