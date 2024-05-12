package marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import org.springframework.stereotype.Component;
/**
 * Mapper da entidade Proprietário
 */
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
