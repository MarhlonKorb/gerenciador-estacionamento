package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.exceptions;

public class ProprietarioNotFoundException extends RuntimeException {

    public ProprietarioNotFoundException() {
        super("Proprietário não encontrado!");
    }
}
