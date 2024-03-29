package marhlonkorb.github.io.gerenciadorestacionamento.core.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
public enum Status {

    A("Ativo"),
    I("Inativo");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

