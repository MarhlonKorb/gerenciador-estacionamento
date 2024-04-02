package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper extends AbstractEntityMapper<Proprietario, ProprietarioInputMapper, ProprietarioOutputMapper> {

    @Override
    public ProprietarioOutputMapper convertToDto(Proprietario input) {
         return getModelMapper().map(input, ProprietarioOutputMapper.class);
    }

    @Override
    public Proprietario convertToEntity(ProprietarioInputMapper input) {
        return getModelMapper().map(input, Proprietario.class);
    }

}
