package marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.IEmailValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.exception.FormatoEmailInvalidoException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidadorImpl implements IUsuarioValidador {
    private final UsuarioRepository usuarioRepository;

    private final IEmailValidador iEmailValidador;

    public UsuarioValidadorImpl(UsuarioRepository usuarioRepository, IEmailValidador iEmailValidador) {
        this.usuarioRepository = usuarioRepository;
        this.iEmailValidador = iEmailValidador;
    }

    @Override
    public void validar(Usuario usuario) {
        iEmailValidador.validar(usuario.getEmail());
        contemPassword(usuario.getPassword());
        validaIsUsuarioExistente(usuario.getEmail());
    }

    @Override
    public void contemPassword(String password) throws UsuarioException {
        if (password == null || password.isEmpty()) {
            throw new UsuarioException("Senha não pode ser vazia.");
        }
    }

    @Override
    public void validaIsUsuarioExistente(String email) {
        final var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if (usuarioEncontrado != null) {
            throw new UsuarioException("Usuário já cadastrado.");
        }
    }

    @Override
    public void validaIsUsuarioInexistente(String email) {
        final var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if (usuarioEncontrado == null) {
            throw new UsuarioException("Usuário não cadastrado.");
        }
    }

}
