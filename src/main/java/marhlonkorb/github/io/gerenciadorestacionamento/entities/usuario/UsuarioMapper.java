package marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario;

import java.util.Optional;

import org.springframework.stereotype.Component;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.UsuarioRepository;

/**
 * Mapper da entidade Usuário
 */
@Component
public class UsuarioMapper extends AbstractEntityMapper<Usuario, UsuarioInputMapper, UsuarioOutputMapper> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioOutputMapper convertToDto(Usuario input) {
        return getModelMapper().map(input, UsuarioOutputMapper.class);
    }

    @Override
    public Usuario convertToEntity(UsuarioInputMapper usuarioInputMapper) {
        setPasswordAntesAtualizarUsuario(usuarioInputMapper);
        return getModelMapper().map(usuarioInputMapper, Usuario.class);
    }

    /**
     * Busca a senha do usuário e adiciona ao objeto usuario que será persistido
     *
     * @param usuarioInputMapper
     */
    private void setPasswordAntesAtualizarUsuario(UsuarioInputMapper usuarioInputMapper) {
        // Id do usuário não será nulo quando for atualização de usuário
        if (usuarioInputMapper.getId() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(usuarioInputMapper.getId());
            usuarioInputMapper.setPassword(usuario.get().getPassword());
        }
    }
}
