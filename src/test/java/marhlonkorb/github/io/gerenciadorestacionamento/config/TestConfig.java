package marhlonkorb.github.io.gerenciadorestacionamento.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.xmlunit.builder.Input;

/**
 * Classe responsável pela configuração da injeção de dependências necessárias para execução dos testes
 */
@Configuration
@Profile("test")
public class TestConfig {

    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private ModelMapper modelMapper;
}
