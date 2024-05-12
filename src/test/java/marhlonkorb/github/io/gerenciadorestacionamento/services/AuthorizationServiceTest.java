package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.builder.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthorizationServiceTest {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void deveRetornarUserDetailsPeloEmailInformadoQuandoUsuarioEstiverCadastrado() {
        // Constrói usuário adicionando o email esperado no resultado do teste
        final var usuario = new UsuarioBuilder().setEmail("teste@teste.com").setPassword("teste").build();
        usuarioService.create(usuario);
        // Executa o método a ser testado
        final UserDetails userDetails = authorizationService.loadUserByUsername(usuario.getUsername());
        // Compara os resultados
        assertEquals(usuario.getUsername(), userDetails.getUsername());
    }

    @Test
    void deveRetornarNuloQuandoUserDetailsNaoForEncontradoPeloUsername() {
        // Executa o método a ser testado
        assertThrows(UsernameNotFoundException.class, () -> authorizationService.loadUserByUsername("usuarioAuthorizationService"));
    }

}