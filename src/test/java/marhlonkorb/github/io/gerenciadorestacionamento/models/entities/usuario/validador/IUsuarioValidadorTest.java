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
    void deveLancarExceptionQuandoPasswordForVazio() {
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.contemPassword(""));
    }

    @Test
    void deveLancarExceptionQuandoPasswordForNulo() {
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.contemPassword(null));
    }

    @Test
    void deveLancarExceptionQuandoUsuarioJaEstiverCadastrado() {
        Mockito.when(usuarioRepository.findByEmail("teste@teste.com.br")).thenReturn(null);
        Assert.assertThrows(UsuarioException.class, ( ) -> iUsuarioValidador.validaIsUsuarioInexistente("teste@teste.com.br"));
    }
    @Test
    void naoDeveLancarExceptionQuandoUsuarioNaoEstiverCadastrado() {
        Usuario usuario = new UsuarioBuilder().setEmail("teste@teste.com.br").build();
        Mockito.when(usuarioRepository.findByEmail("teste@teste.com.br")).thenReturn(usuario);
        assertDoesNotThrow(() -> iUsuarioValidador.validaIsUsuarioInexistente(usuario.getEmail()));
    }
}