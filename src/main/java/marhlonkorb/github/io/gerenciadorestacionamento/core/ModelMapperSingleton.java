package marhlonkorb.github.io.gerenciadorestacionamento.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class ModelMapperSingleton {

    private static ModelMapper instance;

    abstract protected ModelMapper init();

    static protected ModelMapper getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new ModelMapper();
        return instance;
    }

}
