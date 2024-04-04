package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VagaRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VeiculoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
