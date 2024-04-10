/*
 * gerenciador-estacionamento
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityService;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.UsuarioInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.UsuarioOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.exceptions.UsuarioException;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.validador.IUsuarioValidador;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;
import marhlonkorb.github.io.gerenciadorestacionamento.validador.email.IEmailValidador;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service da entidade Usuario
 */
@Service
public class UsuarioService extends AbstractEntityService<Usuario, Long, UsuarioInputMapper, UsuarioOutputMapper> {
    private final UsuarioRepository usuarioRepository;
    private final IUsuarioValidador iUsuarioValidador;
    private final IEmailValidador iEmailValidador;

    public UsuarioService(UsuarioRepository usuarioRepository, IUsuarioValidador iUsuarioValidador, IEmailValidador iEmailValidador) {
        this.usuarioRepository = usuarioRepository;
        this.iUsuarioValidador = iUsuarioValidador;
        this.iEmailValidador = iEmailValidador;
    }

    /**
     * Cria um novo usuário
     *
     * @param usuario
     * @return Usuario
     */
    public Usuario create(Usuario usuario) {
        iUsuarioValidador.validar(usuario);
        String encryptedPassword = getPasswordCriptografado(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        usuario.setStatus(Status.A);
        return usuarioRepository.save(usuario);
    }

    private String getPasswordCriptografado(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public Usuario findById(Long id) {
        var usuarioEncontrado = usuarioRepository.findById(id);
        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioException("Usuário não encontrado.");
        }
        return usuarioEncontrado.get();
    }

    public Usuario findByEmail(String email) {
        iEmailValidador.validar(email);
        var usuarioEncontrado = usuarioRepository.findUsuarioByEmail(email);
        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioException("Usuário não encontrado.");
        }
        return usuarioEncontrado.get();
    }
}
