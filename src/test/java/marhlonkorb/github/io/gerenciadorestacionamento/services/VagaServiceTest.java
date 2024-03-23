package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VagaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VagaServiceTest {
    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private VagaService vagaService;

    @Test
    void deveRetornarVagaQuandoExistirVagaPeloIdInformado() {
        Vaga vagaEsperada = new Vaga();
        vagaEsperada.setId(1L);
        Mockito.when(vagaRepository.findById(1L)).thenReturn(Optional.of(vagaEsperada));
        Vaga vagaEncontrada = vagaService.getVagaById(1L);
        Assertions.assertEquals(vagaEsperada, vagaEncontrada);
    }

    @Test
    void deveLancarExceptionQuandoNaoExistirVagaPeloIdInformado() {
        // Mock retornando Optional.empty() para simular a não existência da vaga
        Mockito.when(vagaRepository.findById(1L)).thenReturn(Optional.empty());
        // Utilizando assertThrows para verificar se uma exceção é lançada quando nenhum vaga é encontrada
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            vagaService.getVagaById(1L);
        });
    }

    @Test
    void deveSalvarERetornarAVagaSalva() {
        // Cria uma instância de Vaga para salvar
        Vaga vagaParaSalvar = new Vaga();
        vagaParaSalvar.setId(1L);
        // Mock do comportamento do repositório para salvar a vaga
        Mockito.when(vagaRepository.save(Mockito.any(Vaga.class))).thenReturn(vagaParaSalvar);
        // Chama o método save() do service
        Vaga vagaSalva = vagaService.save(vagaParaSalvar);
        // Verifica se a vaga retornada é a mesma que foi salva
        Assertions.assertEquals(vagaParaSalvar, vagaSalva);
    }

}