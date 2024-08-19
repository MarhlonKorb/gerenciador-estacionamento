package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por fornecer informações sobre a saúde da aplicação.
 * Implementa a interface HealthIndicator do Spring Boot.
 */
@Component
public class HealthCheck implements HealthIndicator {

    /**
     * Verifica o estado de saúde da aplicação.
     *
     * @return Um objeto Health representando o status de saúde da aplicação.
     */
    @Override
    public Health health() {
        // Executa uma verificação de saúde e obtém o código de erro
        int errorCode = check();

        // Se o código de erro for diferente de zero, indica que a saúde está 'indisponível' com detalhes
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Código de Erro", errorCode)
                    .build();
        }

        // Se o código de erro for zero, indica que a saúde está 'disponível'
        return Health.up().build();
    }

    /**
     * Método de espaço reservado para implementar lógica personalizada de
     * verificação de saúde.
     *
     * @return Um inteiro representando o resultado da verificação de saúde (0
     * para sucesso, diferente de zero para falha).
     */
    public int check() {
        // Implementa a lógica personalizada para verificar a saúde da aplicação
        // Por exemplo, realizar verificações de banco de dados, conectividade com serviços externos, etc.

        // Atualmente, a implementação retorna 0 como um espaço reservado para um estado saudável
        return 0;
    }
}
