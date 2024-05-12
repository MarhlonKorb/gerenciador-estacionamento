package marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo.exceptions;

public class VeiculoNotFoundException extends RuntimeException{
    public VeiculoNotFoundException(String message) {
        super(message);
    }

}
