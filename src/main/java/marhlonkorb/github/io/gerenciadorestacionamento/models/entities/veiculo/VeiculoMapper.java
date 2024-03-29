package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class VeiculoMapper extends AbstractEntityMapper<Veiculo, VeiculoInputMapper, VeiculoOutputMapper> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VeiculoOutputMapper convertToDto(Veiculo input) {
        return modelMapper.map(input, VeiculoOutputMapper.class);
    }

    @Override
    public Veiculo convertToEntity(VeiculoInputMapper input) {
        return modelMapper.map(input, Veiculo.class);
    }

    public Set<VeiculoOutputMapper> convertToSetDto(Set<Veiculo> veiculosProprietario){
        return modelMapper.map(veiculosProprietario, new TypeToken<Set<VeiculoOutputMapper>>() {
        }.getType());
    }
}
