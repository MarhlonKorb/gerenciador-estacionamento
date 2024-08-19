package marhlonkorb.github.io.gerenciadorestacionamento.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Role;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.UsuarioInputCadastro;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.builder.UsuarioBuilder;

@Service
public class CriaUsuarioProprietarioUseCase {

    private final UsuarioService usuarioService;

    private final ProprietarioService proprietarioService;

    public CriaUsuarioProprietarioUseCase(UsuarioService usuarioService, ProprietarioService proprietarioService) {
        this.usuarioService = usuarioService;
        this.proprietarioService = proprietarioService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(UsuarioInputCadastro usuarioInputCadastro) {
        // Se for um usuário comum
        if (usuarioInputCadastro.role().equals(Role.USER)) {
            // Converte em um model de Usuario
            var usuarioConvertido = new UsuarioBuilder()
                    .setEmail(usuarioInputCadastro.email())
                    .setPassword(usuarioInputCadastro.password())
                    .setRole(usuarioInputCadastro.role())
                    .build();
            // Cria o usuário
            usuarioService.create(usuarioConvertido);
            // Cria o proprietário e vincula o usuário criado
            proprietarioService.adicionaUsuarioNovoProprietario(usuarioConvertido);
        }
    }
}
