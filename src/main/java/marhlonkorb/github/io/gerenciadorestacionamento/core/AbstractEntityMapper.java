package marhlonkorb.github.io.gerenciadorestacionamento.core;

import marhlonkorb.github.io.gerenciadorestacionamento.config.ModelMapperConfig;
import org.apache.maven.model.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractEntityMapper<EntidadeComId, Input, DtoType> {

    protected abstract DtoType convertToDto(EntidadeComId input);
    protected abstract EntidadeComId convertToEntity(Input input);

    protected ModelMapper getModelMapper(){
        return new ModelMapperConfig().getInstance();
    }

}
