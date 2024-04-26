/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityController;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.services.VeiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

/**
 * Classe responsável por tratar as requisições Http para a classe Veiculo
 */
@RestController
@RequestMapping("veiculo")
public class VeiculoController extends AbstractEntityController<Veiculo, Long, VeiculoInputMapper, VeiculoOutputMapper> {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/findAllByIdProprietario/{idProprietario}")
    public Set<VeiculoOutputMapper> findAllByIdProprietario(@PathVariable Long idProprietario){
        return veiculoService.findAllByIdProprietario(idProprietario);
    }
    @GetMapping("/findVeiculoPrincipal/{idProprietario}")
    public Optional<VeiculoOutputMapper> findVeiculoPrincipal(@PathVariable Long idProprietario){
        return veiculoService.findVeiculoPrincipal(idProprietario);
    }

    @PutMapping("/selecionaComoPrincipal")
    public void selecionaComoPrincipal(@RequestBody VeiculoInputMapper veiculo){
        veiculoService.updateVeiculoPrincipal(veiculo);
    }

}
