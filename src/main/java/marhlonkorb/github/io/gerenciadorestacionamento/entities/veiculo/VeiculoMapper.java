package marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo;

import java.util.Set;

import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;

/**
 * Mapper da entidade Veículo
 */
@Component
public class VeiculoMapper extends AbstractEntityMapper<Veiculo, VeiculoInputMapper, VeiculoOutputMapper> {

    @Override
    public VeiculoOutputMapper convertToDto(Veiculo input) {
        return getModelMapper().map(input, VeiculoOutputMapper.class);
    }

    @Override
    public Veiculo convertToEntity(VeiculoInputMapper input) {
        return getModelMapper().map(input, Veiculo.class);
    }

    public Set<VeiculoOutputMapper> convertToSetDto(Set<Veiculo> veiculosProprietario) {
        return getModelMapper().map(veiculosProprietario, new TypeToken<Set<VeiculoOutputMapper>>() {
        }.getType());
    }
}
