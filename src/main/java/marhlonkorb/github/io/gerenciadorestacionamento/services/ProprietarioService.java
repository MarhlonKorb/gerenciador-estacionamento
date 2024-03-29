package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.AbstractEntityService;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.ProprietarioInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.ProprietarioMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.ProprietarioOutputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.ProprietarioRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

/**
 * Service da entidade Proprietario
 */
@Service
public class ProprietarioService extends AbstractEntityService<Proprietario, Long, ProprietarioInputMapper, ProprietarioOutputMapper> {
    private final ProprietarioMapper proprietarioMapper;

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioMapper proprietarioMapper, ProprietarioRepository proprietarioRepository) {
        this.proprietarioMapper = proprietarioMapper;
        this.proprietarioRepository = proprietarioRepository;
    }

    /**
     * Busca o proprietário pelo id
     *
     * @param idProprietario
     * @return Proprietario
     */
    public Proprietario getProprietarioById(Long idProprietario) {
        return proprietarioRepository.findById(idProprietario)
                .orElseThrow(() -> new InvalidDataAccessApiUsageException("Proprietário não encontrado."));
    }

    public Proprietario save(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    public ProprietarioOutputMapper getProprietarioByIdUsuario(Long idUsuario){
        final Proprietario proprietarioEncontrado = proprietarioRepository.getByUsuarioId(idUsuario);
        return proprietarioMapper.convertToDto(proprietarioEncontrado);
    }

    /**
     * Cria o vínculo do usuário com um novo proprietário
     *
     * @param usuario
     */
    public void adicionaUsuarioNovoProprietario(Usuario usuario){
        Proprietario proprietario = new Proprietario();
        proprietario.setUsuario(usuario);
        proprietario.setNome(usuario.getEmail());
        save(proprietario);
    }
}
