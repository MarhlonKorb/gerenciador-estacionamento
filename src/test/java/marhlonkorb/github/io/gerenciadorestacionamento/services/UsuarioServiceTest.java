package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.builder.UsuarioBuilder;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador.IUsuarioValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador.UsuarioValidadorImpl;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.EmailValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.IEmailValidador;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private IUsuarioValidador iUsuarioValidador;

    @Mock
    private IEmailValidador iEmailValidador;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveCriarUsuarioAtivoQuandoExecutarMetodoCreate(){
        Usuario usuario = new UsuarioBuilder().setEmail("teste@teste.com").setPassword("teste").build();
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        // Executa método a ser testado
        usuarioService.create(usuario);
        // Não deve lançar exception durante a execução do método
        assertDoesNotThrow(() -> usuarioService.create(usuario));
        // Deve ser criado usuário com Status Ativo
        Assert.assertTrue(usuario.getStatus().equals(Status.A));
    }

    @Test
    void deveLancarExceptionQuandoPasswordForNulo(){
        // Deve lançar exception durante a execução do método
        Assert.assertThrows(IllegalArgumentException.class, () -> usuarioService.create(new Usuario()));
    }

    @Test
    void deveCriarUsuarioQuandoSenhaNaoForNula(){
        Usuario usuario = new UsuarioBuilder().setPassword("teste").build();
        // Não deve lançar exception durante a execução do método
       assertDoesNotThrow(() -> usuarioService.create(usuario));
    }

}