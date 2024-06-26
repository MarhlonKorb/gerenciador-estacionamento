package marhlonkorb.github.io.gerenciadorestacionamento.services;

import jakarta.transaction.Transactional;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.proprietario.exceptions.ProprietarioNotFoundException;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProprietarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProprietarioService proprietarioService;

    @Test
    void deveRetornarProprietarioPeloId() {
        // Cria um proprietário
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("proprietario1");
        var proprietarioCriado = proprietarioService.save(proprietario);
        // Executa o método a ser testado
        Proprietario proprietarioEncontrado = proprietarioService.getProprietarioById(proprietarioCriado.getId());
        assertNotNull(proprietarioEncontrado);
        assertEquals(proprietarioCriado.getId(), proprietarioEncontrado.getId());
    }

    @Test
    void deveLancarExceptionQuandoNaoEncontrarProprietarioPeloId() {
        Long idProprietario = 10L;
        assertThrows(ProprietarioNotFoundException.class, () -> proprietarioService.getProprietarioById(idProprietario));
    }

    @Test
    void deveRetornarProprietarioSalvo() {
        // Cria um proprietário
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("proprietario2");
        // Executa método a ser testado
        Proprietario result = proprietarioService.save(proprietario);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void deveAdicionarUsuarioNovoProprietario() {
        // Cria usuário
        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioproprietario1@teste.com");
        usuario.setPassword("teste");
        var usuarioCriado = usuarioService.create(usuario);
        // Cria um proprietário
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("proprietario2");
        Proprietario proprietarioCriado = proprietarioService.save(proprietario);
        // Executa o método a ser testado
        proprietarioService.adicionaUsuarioNovoProprietario(usuarioCriado);
        var proprietarioEncontrado = proprietarioService.getProprietarioById(proprietarioCriado.getId());
        // Then
        assertNotNull(proprietarioEncontrado);
        assertNotNull(proprietarioEncontrado.getId());
    }

    @Test
    @Transactional
    void deveRetornarOutputMapperDoProprietarioQuandoForEncontradoProprietarioPeloIdUsuario() {
        // Cria usuário
        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioproprietario5@teste.com");
        usuario.setPassword("teste");
        var usuarioCriado = usuarioService.create(usuario);
        proprietarioService.adicionaUsuarioNovoProprietario(usuarioCriado);
        // Executa o método a ser testado
        var proprietarioOutput = proprietarioService.getProprietarioByIdUsuario(usuarioCriado.getId());
        assertNotNull(proprietarioOutput);
        assertEquals(usuarioCriado.getId(), proprietarioOutput.getIdUsuario());
    }

    @Test
    @Transactional
    void deveLancarExceptionQuandoNaoForEncontradoProprietarioPeloIdUsuario() {
        // Cria usuário
        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioproprietario5@teste.com");
        usuario.setPassword("teste");
        var usuarioCriado = usuarioService.create(usuario);
        // Executa o método a ser testado
        assertThrows(ProprietarioNotFoundException.class, () -> proprietarioService.getProprietarioByIdUsuario(usuarioCriado.getId()));
    }
}
