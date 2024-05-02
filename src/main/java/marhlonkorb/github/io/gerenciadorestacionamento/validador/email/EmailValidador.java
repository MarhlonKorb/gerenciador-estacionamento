package marhlonkorb.github.io.gerenciadorestacionamento.validador.email;

import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email.IEmailValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String EMAIL_OBRIGATORIO_KEY = "campo.email.obrigatorio";
    private static final String EMAIL_INVALIDO_KEY = "campo.email.invalido";

    private final MessageUtil messageUtil;

    private final UsuarioRepository usuarioRepository;

    public EmailValidador(MessageUtil messageUtil, UsuarioRepository usuarioRepository) {
        this.messageUtil = messageUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void validar(String email) {
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        try {
            if (email == null || email.isEmpty()) {
                throw new FormatoEmailInvalidoException(messageUtil.getMessage(EMAIL_OBRIGATORIO_KEY));
            }
            final Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new FormatoEmailInvalidoException(messageUtil.getMessage(EMAIL_INVALIDO_KEY));
            }
        } catch (RuntimeException e) {
            throw new FormatoEmailInvalidoException(e.getMessage());
        }
    }

    @Override
    public boolean isEmailCadastrado(String email) {
        final var usuarioEncontrado = usuarioRepository.findUsuarioByEmail(email);
        if (usuarioEncontrado.isPresent()) {
            return true;
        }
        return false;
    }

}
