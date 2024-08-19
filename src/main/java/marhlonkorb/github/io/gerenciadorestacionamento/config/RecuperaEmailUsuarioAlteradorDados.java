package marhlonkorb.github.io.gerenciadorestacionamento.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por recuperar o email do usuário que está persistindo a entidade auditada atual
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
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        // Verifica se o usuário autenticado é do tipo UserDetails (ou uma subclasse)
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return Optional.of(userDetails.getUsername());
        } else {
            // Caso não seja do tipo UserDetails, você pode adaptar para suas necessidades,
            // como verificar se o principal é uma instância de sua classe de usuário personalizada.
            return Optional.empty();
        }
    }
}
