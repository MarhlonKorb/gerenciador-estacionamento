package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.builder.UsuarioBuilder;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.usuario.IUsuarioValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email.IEmailValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UsuarioServiceTest {

    @Autowired
    private IUsuarioValidador iUsuarioValidador;

    @Autowired
    private IEmailValidador iEmailValidador;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void deveCriarUsuarioComStatusAtivoQuandoUsuarioForValido() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste1@teste.com").setPassword("teste").build();
        // Não deve lançar exception durante a execução do método
        assertDoesNotThrow(() -> usuarioService.create(usuario));
        // Deve ser criado usuário com Status Ativo
        Assert.assertTrue(usuario.getStatus().equals(Status.A));
    }

    @Test
    void deveCriptografarSenhaQuandoCriarNovoUsuario() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste2@teste.com").setPassword("teste").build();
        var senhaInicial = usuario.getPassword();
        // Não deve lançar exception durante a execução do método
        var usuarioCriado = usuarioService.create(usuario);
        // Deve ser criado usuário com Status Ativo
        Assert.assertNotEquals(senhaInicial, usuarioCriado.getPassword());
    }

    @Test
    void deveLancarExceptionQuandoUsuarioJaEstiverCadastrado() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste3@teste.com").setPassword("teste").build();
        // Executa método a ser testado
        usuarioService.create(usuario);
        // Deve lançar exception durante a execução do método
        assertThrows(UsuarioException.class, () -> usuarioService.create(usuario));
    }

    @Test
    void deveLancarExceptionQuandoPasswordForNulo() {
        // Deve lançar exception durante a execução do método
        Assert.assertThrows(FormatoEmailInvalidoException.class, () -> usuarioService.create(new Usuario()));
    }

    @Test
    void deveCriarUsuarioQuandoEmailESenhaNaoForemNulos() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste4@teste.com").setPassword("teste").build();
        ;
        // Não deve lançar exception durante a execução do método
        assertDoesNotThrow(() -> usuarioService.create(usuario));
    }

    @Test
    void deveRetornarUsuarioPeloIdQuandoUsuarioForEncontradoPeloId() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste10@teste.com").setPassword("teste").build();
        var usuarioCriado = usuarioService.create(usuario);
        var usuarioEncontrado = usuarioService.findById(usuarioCriado.getId());
        assertEquals(usuarioCriado.getId(), usuarioEncontrado.getId());
        assertEquals(usuarioCriado.getEmail(), usuarioEncontrado.getEmail());
    }

    @Test
    void deveLancarExceptionQuandoUsuarioNaoEstiverCadastrado() {
        assertThrows(UsuarioException.class, () -> usuarioService.findById(12L));
    }

    @Test
    void deveRetornarUsuarioPeloEmailQuandoUsuarioForEncontrado() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste11@teste.com").setPassword("teste").build();
        var usuarioCriado = usuarioService.create(usuario);
        var usuarioEncontrado = usuarioService.findByEmail(usuarioCriado.getEmail());
        assertEquals(usuarioCriado.getId(), usuarioEncontrado.getId());
        assertEquals(usuarioCriado.getEmail(), usuarioEncontrado.getEmail());
    }

    @Test
    void deveLancarExceptionQuandoUsuarioNaoForEncontradoUsuarioComEmailInformado() {
        assertThrows(UsuarioException.class, () -> usuarioService.findByEmail("teste@varP.com.br"));
    }
}