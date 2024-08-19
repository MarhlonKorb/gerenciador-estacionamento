package marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email;

/**
 * Interface que define contrato para as validações de email
 */
public interface IEmailValidador {

    void validar(String email);

    public boolean isEmailCadastrado(String email);

}
