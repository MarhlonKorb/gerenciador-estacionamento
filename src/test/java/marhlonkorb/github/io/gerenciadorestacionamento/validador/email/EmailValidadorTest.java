package marhlonkorb.github.io.gerenciadorestacionamento.validador.email;

import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email.IEmailValidador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmailValidadorTest {

    @Autowired
    private IEmailValidador iEmailValidador;

    @Autowired
    private MessageUtil messageUtil;

    @Test
    void deveLancarExceptionQuandoEmailForNulo(){
        // Assertiva para verificar que a exceção esperada foi lançada
        Exception exception = assertThrows(RuntimeException.class, () -> iEmailValidador.validar(null));
        // Verifica se a mensagem da exceção é a esperada
        assertEquals(messageUtil.getMessage("campo.email.obrigatorio"), exception.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoEmailForVazio(){
        // Assertiva para verificar que a exceção esperada foi lançada
        Exception exception = assertThrows(RuntimeException.class, () -> iEmailValidador.validar(""));
        // Verifica se a mensagem da exceção é a esperada
        assertEquals(messageUtil.getMessage("campo.email.obrigatorio"), exception.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoFormatoEmailForInvalido(){
        // Assertiva para verificar que a exceção esperada foi lançada
        Exception exception = assertThrows(RuntimeException.class, () -> iEmailValidador.validar("teste"));
        // Verifica se a mensagem da exceção é a esperada
        assertEquals(messageUtil.getMessage("campo.email.invalido"), exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoFormatoEmailForValido(){
        // Verifica que nenhuma exceção foi lançada
        assertDoesNotThrow(() -> iEmailValidador.validar("teste@teste.com"));
    }
}
