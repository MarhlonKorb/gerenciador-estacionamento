/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityController;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.ProprietarioDbConstantes;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.ProprietarioInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.ProprietarioOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.exceptions.ProprietarioNotFoundException;
import marhlonkorb.github.io.gerenciadorestacionamento.services.ProprietarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controla as requisições da entidade Proprietario
 */
@RestController
@RequestMapping(ProprietarioDbConstantes.TABLE_NAME)
public class ProprietarioController extends AbstractEntityController<Proprietario, Long, ProprietarioInputMapper, ProprietarioOutputMapper> {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping("/getProprietarioPeloIdUsuario={id}")
    public ResponseEntity<ProprietarioOutputMapper> getIdProprietarioPeloIdUsuario(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(proprietarioService.getProprietarioByIdUsuario(id));
        } catch (ProprietarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
