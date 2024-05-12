/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.rest.controllers;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityController;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.UsuarioInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.UsuarioOutputMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por tratar as requisições Http para a classe Usuário
 */
@RestController
@RequestMapping("usuario")
public class UsuarioController extends AbstractEntityController<Usuario, Long, UsuarioInputMapper, UsuarioOutputMapper> {

}
