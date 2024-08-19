package marhlonkorb.github.io.gerenciadorestacionamento.core.enums;

public enum Status {

    A("Ativo"),
    I("Inativo");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
