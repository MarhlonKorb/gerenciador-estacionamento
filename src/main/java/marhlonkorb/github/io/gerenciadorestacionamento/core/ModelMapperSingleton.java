package marhlonkorb.github.io.gerenciadorestacionamento.core;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public abstract class ModelMapperSingleton {

    private static ModelMapper instance;

    abstract protected ModelMapper init();

    public ModelMapper getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new ModelMapper();
        applyConfig();
        return instance;
    }

    /**
     * Inicialização da configuração.
     * Compara campos privados nas classes de mapeamento (objetos).
     * Nesta configuração, não é estritamente necessário que todos os campos com os mesmos nomes existam em ambas as classes.
     *  https://www.baeldung.com/java-modelmapper-lists
     */
    private void applyConfig() {
        instance.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
}
