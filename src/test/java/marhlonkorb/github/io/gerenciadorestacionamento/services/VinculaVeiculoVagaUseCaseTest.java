package marhlonkorb.github.io.gerenciadorestacionamento.services;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.StatusVaga;
import marhlonkorb.github.io.gerenciadorestacionamento.core.initializer.DataInitializer;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.exceptions.VeiculoNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class VinculaVeiculoVagaUseCaseTest {

    @Autowired
    private VinculaVeiculoVagaUseCase vinculaVeiculoVagaUseCase;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VagaService vagaService;

    @Before
    public void setUp() throws Exception {
        new DataInitializer();
    }

    @Test
    @Transactional
    void deveExecutarVinculoVeiculoVagaQuandoVeiculoNaoTiverVinculoComVagaEVagaNaoTiverVinculoComVeiculo() {
        final var idVaga = 1L;
        // Cria um veículo
        var veiculoCriado = veiculoService.save(createVeiculo());
        var vagaSemVinculo = vagaService.findById(idVaga);
        // Status da vaga inicializa como Livre
        assertEquals(vagaSemVinculo.getStatusVaga(), StatusVaga.L);
        // Executa método a ser testado
        vinculaVeiculoVagaUseCase.execute(veiculoCriado.getId(), idVaga);
        // Busca o veículo criado
        var veiculoComVinculo = veiculoService.findById(veiculoCriado.getId());
        // Busca a vaga
        var vagaComVinculo = vagaService.findById(idVaga);
        // Valida que o veículo foi vinculado a vaga e a vaga ao veículo
        assertEquals(vagaComVinculo.getVeiculo(), veiculoComVinculo);
        assertEquals(veiculoComVinculo.getVaga(), vagaComVinculo);
        // Valida que o status da vaga foi alterado de Livre para Ocupada
        assertEquals(vagaComVinculo.getStatusVaga(), StatusVaga.O);
    }

    @Test
    @Transactional
    void deveLancarExceptionQuandoVeiculoNaoForEncontrado() {
        final var idVaga = 2L;
        var veiculoAlterado = createVeiculo();
        // Seta o id do Veículo para um inexistente no banco
        veiculoAlterado.setId(2L);
        assertThrows(VeiculoNotFoundException.class, () -> vinculaVeiculoVagaUseCase.execute(veiculoAlterado.getId(), idVaga));
    }

    @Test
    @Transactional
    void naoDeveExecutarVinculoVeiculoVagaQuandoVeiculoTiverVinculoComVagaEVagaTiverVinculoComVeiculo() {
        final var idVaga = 1L;
        // Cria um veículo
        var veiculoCriado = veiculoService.save(createVeiculo());
        // Executa método a ser testado
        vinculaVeiculoVagaUseCase.execute(veiculoCriado.getId(), idVaga);
        // Busca o veículo criado
        var veiculoComVinculo = veiculoService.findById(veiculoCriado.getId());
        // Busca a vaga com vínculo do veiculo
        var vagaComVinculo = vagaService.findById(idVaga);// Busca a vaga
        // Busca a vaga sem vínculo do veiculo
        var vagaSemVinculo = vagaService.findById(2L);
        vinculaVeiculoVagaUseCase.execute(veiculoCriado.getId(), vagaSemVinculo.getId());
        // Valida que o veículo vinculado a uma vaga não foi vinculado a outra
        assertEquals(veiculoComVinculo.getVaga(), vagaComVinculo);
        assertNotEquals(veiculoComVinculo.getVaga().getId(), vagaSemVinculo.getId());
    }

    private Veiculo createVeiculo() {
        return new Veiculo("placa1", "marca1", "modelo1");
    }
}