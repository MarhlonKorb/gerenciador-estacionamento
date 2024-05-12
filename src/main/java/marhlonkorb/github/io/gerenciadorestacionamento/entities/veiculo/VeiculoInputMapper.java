package marhlonkorb.github.io.gerenciadorestacionamento.entities.veiculo;

/**
 * Input da entidade Veiculo
 */
public class VeiculoInputMapper {

    private Long id;

    private Long idProprietario;

    private String placa;

    private String marca;

    private String modelo;

    private String ano;

    private boolean principal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
