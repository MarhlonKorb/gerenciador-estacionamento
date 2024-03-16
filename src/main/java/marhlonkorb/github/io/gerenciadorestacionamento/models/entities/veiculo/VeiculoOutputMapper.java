package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.VagaOutputMapper;

/**
 * Output da entidade Veiculo
 */
public class VeiculoOutputMapper {

    private Long id;

    private String placa;

    private String marca;

    private String modelo;

    private String ano;
    
    private VagaOutputMapper vaga;

    private Long idProprietario;

    private boolean isPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public VagaOutputMapper getVaga() {
        return vaga;
    }

    public void setVaga(VagaOutputMapper vaga) {
        this.vaga = vaga;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }
}
