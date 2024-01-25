package marhlonkorb.github.io.gerenciadorestacionamento.core.enums;

public enum Role {
    // AE = Admin Empresa
    // UM = Usuario Comum
    ADMIN("Admin"), USER("Usuário comum");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
