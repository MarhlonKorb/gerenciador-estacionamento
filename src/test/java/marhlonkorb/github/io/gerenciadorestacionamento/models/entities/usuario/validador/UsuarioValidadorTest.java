package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador;

import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.usuario.IUsuarioValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.builder.UsuarioBuilder;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.services.UsuarioService;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email.IEmailValidador;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
class UsuarioValidadorTest {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IEmailValidador iEmailValidador;
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IUsuarioValidador iUsuarioValidador;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void naoDeveLancarExceptionQuandoForInformadoPassword() {
        assertDoesNotThrow(() -> iUsuarioValidador.contemPassword("teste"));
    }

    @Test
    void deveLancarExceptionQuandoPasswordForNulo() {
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.contemPassword(null));
    }

    @Test
    void deveLancarExceptionQuandoPasswordForVazio() {
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.contemPassword(""));
    }

    @Test
    void deveLancarExceptionQuandoUsuarioJaEstiverCadastrado() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste@teste.com.br").setPassword("teste").build();
        var usuarioCriado = usuarioRepository.save(usuario);
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.validaUsuarioIsCadastrado(usuarioCriado.getEmail()));
    }

    @Test
    void naoDeveLancarExceptionQuandoUsuarioForValido() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste12@teste.com").setPassword("teste").build();
        assertDoesNotThrow(() -> iUsuarioValidador.validar(usuario));
    }
}