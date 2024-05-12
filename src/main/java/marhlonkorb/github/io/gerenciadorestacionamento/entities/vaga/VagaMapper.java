package marhlonkorb.github.io.gerenciadorestacionamento.entities.vaga;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import org.springframework.stereotype.Component;
/**
 * Mapper da entidade Vaga
 */
@Component
public class VagaMapper extends AbstractEntityMapper<Vaga, VagaInputMapper, VagaOutputMapper> {

    @Override
    public VagaOutputMapper convertToDto(Vaga input) {
        return getModelMapper().map(input, VagaOutputMapper.class);
    }

    @Override
    public Vaga convertToEntity(VagaInputMapper input) {
        return getModelMapper().map(input, Vaga.class);
    }

}
