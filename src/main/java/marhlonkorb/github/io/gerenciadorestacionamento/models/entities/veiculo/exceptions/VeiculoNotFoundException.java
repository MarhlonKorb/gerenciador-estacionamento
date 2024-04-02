package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.exceptions;

public class VeiculoNotFoundException extends RuntimeException{
    public VeiculoNotFoundException() {
        super("Veículo não encontrado.");
    }

}
