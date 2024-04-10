package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.UsuarioInputCadastro;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class CriaUsuarioProprietarioUseCaseTest {

    @Autowired
    private CriaUsuarioProprietarioUseCase criaUsuarioProprietarioUseCase;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProprietarioService proprietarioService;

    @Test
    void deveCriarUsuarioQuandoInformadoUsuarioInputCadastro() {
        var usuarioInput = new UsuarioInputCadastro("teste@teste123.com", "teste", Role.USER);
        // Executa método a ser testado
        criaUsuarioProprietarioUseCase.execute(usuarioInput);
        var usuarioEncontrado = usuarioService.findByEmail("teste@teste123.com");
        assertEquals(usuarioEncontrado.getEmail(), usuarioInput.email());
    }

    @Test
    @Transactional
    void deveAdicionarUsuarioANovoProprietarioQuandoInformadoUsuarioInputCadastro() {
        var usuarioInput = new UsuarioInputCadastro("teste@teste1234.com", "teste", Role.USER);
        // Executa método a ser testado
        criaUsuarioProprietarioUseCase.execute(usuarioInput);
        var usuarioEncontrado = usuarioService.findByEmail("teste@teste1234.com");
        var proprietarioCriado = proprietarioService.getProprietarioByIdUsuario(usuarioEncontrado.getId());
        assertNotNull(proprietarioCriado);
        assertEquals(usuarioEncontrado.getId(), proprietarioCriado.getIdUsuario());
    }
}