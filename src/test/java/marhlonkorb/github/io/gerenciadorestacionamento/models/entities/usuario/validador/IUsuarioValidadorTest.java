package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.builder.UsuarioBuilder;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.EmailValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.IEmailValidador;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class IUsuarioValidadorTest {
    final String EMAIL = "teste@teste.com.br";
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private IEmailValidador iEmailValidador = new EmailValidador();

    private IUsuarioValidador iUsuarioValidador;

    @BeforeEach
    void setUp() {
        iUsuarioValidador = new UsuarioValidadorImpl(usuarioRepository, iEmailValidador);
    }

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
        Usuario usuario = new UsuarioBuilder().setEmail("teste@teste.com.br").build();
        Mockito.when(usuarioRepository.findByEmail("teste@teste.com.br")).thenReturn(usuario);
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.validaIsUsuarioExistente(EMAIL));
    }

    @Test
    void naoDeveLancarExceptionQuandoUsuarioNaoEstiverCadastrado() {
        assertDoesNotThrow(() -> iUsuarioValidador.validaIsUsuarioExistente(EMAIL));
    }

    @Test
    void deveLancarExceptionQuandoUsuarioNaoEstiverCadastrado() {
        Mockito.when(usuarioRepository.findByEmail(EMAIL)).thenReturn(null);
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.validaIsUsuarioInexistente(EMAIL));
    }
    @Test
    void naoDeveLancarExceptionQuandoUsuarioJaEstiverCadastrado() {
        Usuario usuario = new UsuarioBuilder().setEmail(EMAIL).build();
        Mockito.when(usuarioRepository.findByEmail(EMAIL)).thenReturn(usuario);
        assertDoesNotThrow(() -> iUsuarioValidador.validaIsUsuarioInexistente(usuario.getEmail()));
    }

    @Test
    void naoDeveLancarExceptionQuandoForExecutadoOMetodoValidar() {
        Usuario usuario = new UsuarioBuilder().setEmail(EMAIL).setPassword("teste").build();
        Mockito.when(usuarioRepository.findByEmail("teste@teste.com.br")).thenReturn(null);
        assertDoesNotThrow(() -> iUsuarioValidador.validar(usuario));
    }
}