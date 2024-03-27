package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.ProprietarioMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.usuario.Usuario;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.ProprietarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProprietarioServiceTest {

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.ProprietarioMapper proprietarioMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProprietarioService proprietarioService;

    @BeforeEach
    void setUp() {
        proprietarioMapper = new ProprietarioMapper(new ModelMapper());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarProprietarioPeloId() {
        // Given
        Long idProprietario = 1L;
        Proprietario proprietario = new Proprietario();
        proprietario.setId(idProprietario);
        // Mocking
        when(proprietarioRepository.findById(idProprietario)).thenReturn(Optional.of(proprietario));
        // When
        Proprietario result = proprietarioService.getProprietarioById(idProprietario);
        // Then
        assertNotNull(result);
        assertEquals(idProprietario, result.getId());
    }

    @Test
    void deveLancarExceptionQuandoNaoEncontrarProprietarioPeloId() {
        // Given
        Long idProprietario = 1L;
        // Mocking
        when(proprietarioRepository.findById(idProprietario)).thenReturn(Optional.empty());
        // When & Then
        assertThrows(InvalidDataAccessApiUsageException.class, () -> proprietarioService.getProprietarioById(idProprietario));
    }

    @Test
    void deveRetornarProprietarioSalvo() {
        // Given
        Proprietario proprietario = new Proprietario();
        // Mocking
        when(proprietarioRepository.save(proprietario)).thenReturn(proprietario);
        // When
        Proprietario result = proprietarioService.save(proprietario);
        // Then
        assertNotNull(result);
        assertEquals(proprietario, result);
    }

    @Test
    void deveAdicionarUsuarioNovoProprietario() {
        // Given
        Usuario usuario = new Usuario();
        // When
        proprietarioService.adicionaUsuarioNovoProprietario(usuario);
        // Then
        verify(proprietarioRepository, times(1)).save(any());
    }
}
