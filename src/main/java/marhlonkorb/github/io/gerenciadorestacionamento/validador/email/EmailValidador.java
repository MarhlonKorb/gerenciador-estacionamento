package marhlonkorb.github.io.gerenciadorestacionamento.validador.email;

import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que executa a validação de email
 */
@Component
public class EmailValidador implements IEmailValidador {
    /* Regex para comparar a formatação do email recebido */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validar(String email) {
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        try {
            if (email == null || email.isEmpty()) {
                throw new FormatoEmailInvalidoException("E-mail é obrigatório.");
            }
            final Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new FormatoEmailInvalidoException("Formato de e-mail inválido.");
            }
        } catch (FormatoEmailInvalidoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
