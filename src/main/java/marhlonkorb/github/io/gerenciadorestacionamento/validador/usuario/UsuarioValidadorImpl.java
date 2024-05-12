package marhlonkorb.github.io.gerenciadorestacionamento.validador.usuario;

import marhlonkorb.github.io.gerenciadorestacionamento.core.utils.MessageUtil;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.usuario.IUsuarioValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.core.validador.email.IEmailValidador;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidadorImpl implements IUsuarioValidador {
    private static final String USUARIO_JA_CADASTRADO_KEY = "exception.usuario.ja.cadastrado";
    private static final String USUARIO_NAO_CADASTRADO_KEY = "exception.usuario.nao.cadastrado";
    private static final String CAMPO_SENHA_OBRIGATORIO_KEY = "campo.senha.obrigatorio";
    private final IEmailValidador iEmailValidador;

    private final MessageUtil messageUtil;

    public UsuarioValidadorImpl(IEmailValidador iEmailValidador, MessageUtil messageUtil) {
        this.iEmailValidador = iEmailValidador;
        this.messageUtil = messageUtil;
    }
    @Override
    public void validar(Usuario usuario) {
        iEmailValidador.validar(usuario.getEmail());
        contemPassword(usuario.getPassword());
        validaUsuarioIsCadastrado(usuario.getEmail());
    }

    @Override
    public void validaUsuarioIsCadastrado(String email) {
        if (iEmailValidador.isEmailCadastrado(email)) {
            throw new UsuarioException(messageUtil.getMessage(USUARIO_JA_CADASTRADO_KEY));
        }
    }

    @Override
    public void validaUsuarioIsNotCadastrado(String email) {
        if (!iEmailValidador.isEmailCadastrado(email)) {
            throw new UsuarioException(messageUtil.getMessage(USUARIO_NAO_CADASTRADO_KEY));
        }
    }

    @Override
    public void contemPassword(String password) throws UsuarioException {
        if (password == null || password.isEmpty()) {
            throw new UsuarioException(messageUtil.getMessage(CAMPO_SENHA_OBRIGATORIO_KEY));
        }
    }

}
