package marhlonkorb.github.io.gerenciadorestacionamento.config;
import marhlonkorb.github.io.gerenciadorestacionamento.core.ModelMapperSingleton;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig extends ModelMapperSingleton {

    @Bean
    public ModelMapper init() {
        return getInstance();
    }
}
