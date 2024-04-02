package marhlonkorb.github.io.gerenciadorestacionamento.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperSingleton {

    private static ModelMapper instance;

    public ModelMapper getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new ModelMapper();
        return instance;
    }
}
