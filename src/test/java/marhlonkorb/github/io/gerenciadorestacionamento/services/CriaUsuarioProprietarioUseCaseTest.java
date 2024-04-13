package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.UsuarioInputCadastro;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CriaUsuarioProprietarioUseCaseTest {

    @Autowired
    private CriaUsuarioProprietarioUseCase criaUsuarioProprietarioUseCase;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private MessageUtil messageUtil;

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

    @Test
    void deveLancarExceptionQuandoInformadoUsuarioComEmailNulo() {
        var usuarioInput = new UsuarioInputCadastro(null, "teste", Role.USER);
        // Executa método a ser testado
        Throwable exception = assertThrows(FormatoEmailInvalidoException.class, () -> criaUsuarioProprietarioUseCase.execute(usuarioInput));
        assertEquals(messageUtil.getMessage("campo.email.obrigatorio"), exception.getLocalizedMessage());
    }

    @Test
    void deveLancarExceptionQuandoInformadoUsuarioComEmailVazio() {
        var usuarioInput = new UsuarioInputCadastro("", "teste", Role.USER);
        // Executa método a ser testado
        Throwable exception = assertThrows(FormatoEmailInvalidoException.class, () -> criaUsuarioProprietarioUseCase.execute(usuarioInput));
        assertEquals(messageUtil.getMessage("campo.email.obrigatorio"), exception.getLocalizedMessage());
    }
    @Test
    void deveLancarExceptionQuandoInformadoUsuarioComPasswordVazio() {
        var usuarioInput = new UsuarioInputCadastro("teste@teste1234.com", "", Role.USER);
        // Executa método a ser testado
        Throwable exception = assertThrows(UsuarioException.class, () -> criaUsuarioProprietarioUseCase.execute(usuarioInput));
        assertEquals(messageUtil.getMessage("campo.senha.obrigatorio"), exception.getLocalizedMessage());
    }
}