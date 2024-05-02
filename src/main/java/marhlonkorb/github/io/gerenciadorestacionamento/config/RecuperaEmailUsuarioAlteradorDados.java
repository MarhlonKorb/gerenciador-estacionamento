package marhlonkorb.github.io.gerenciadorestacionamento.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Classe responsável por recuperar o email do usuário que está persisntindo entidade auditada atual
 */
@Component
public class RecuperaEmailUsuarioAlteradorDados implements AuditorAware<String> {

    /**
     * Recupera o email do usuário que está alterando os dados de determinada entidade auditada
     *
     * @return Optional<String>
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication.getName());
    }
}
