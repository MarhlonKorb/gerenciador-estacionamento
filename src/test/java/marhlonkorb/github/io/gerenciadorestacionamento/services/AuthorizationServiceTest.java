package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.builder.UsuarioBuilder;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @InjectMocks
    AuthorizationService authorizationService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveRetornarUserDetailsPeloEmailInformado() {
        // Constrói usuário adicionando o email esperado no resultado do teste
        final Usuario usuario = new UsuarioBuilder().setEmail("teste@teste.com").build();
        // Quando o método findByEmail for chamado, deve retornar o usuário com email criado
        Mockito.when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(usuario);
        // Executa o método a ser testado
        final UserDetails userDetails = authorizationService.loadUserByUsername(usuario.getUsername());
        // Compara os resultados
        assertEquals(usuario.getUsername(), userDetails.getUsername());
    }

    @Test
    void naoDeveRetornarUserDetailsQuandoEmailNaoForInformado() {
        // Constrói usuário sem nenhum dado
        final Usuario usuario = new Usuario();
        // Quando o método findByEmail for chamado, nao deve retornar usuário
        Mockito.when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(usuario);
        // Executa o método a ser testado
        final UserDetails userDetails = authorizationService.loadUserByUsername(usuario.getUsername());
        // Compara o resultado
        assertNull(userDetails.getUsername());
    }
}