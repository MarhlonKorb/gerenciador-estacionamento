package marhlonkorb.github.io.gerenciadorestacionamento.services;

import jakarta.validation.ConstraintViolationException;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.proprietario.Proprietario;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.Veiculo;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.VeiculoInputMapper;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.veiculo.exceptions.VeiculoNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void deveRetornarVeiculoCriadoPeloId() {
        var veiculoCriado = veiculoService.save(new Veiculo("placa1", "marca1", "modelo1"));
        var veiculoEncontrado = veiculoService.findById(veiculoCriado.getId());
        assertEquals(veiculoCriado.getId(), veiculoEncontrado.getId());
    }

    @Test
    void deveLancarExceptionQuandoNaoEncontrarVeiculoPeloId() {
        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(10L));
    }

    @Test
    void deveRetornarVeiculosPeloIdProprietario() {
        var proprietario = new Proprietario();
        proprietario.setNome("proprietario1");
        // Cria os veículos
        var veiculo1 = new Veiculo("placa1", "Marca1", "Modelo1");
        veiculo1.setProprietario(proprietario);
        var veiculo2 = new Veiculo("placa2", "Marca2", "Modelo2");
        veiculo2.setProprietario(proprietario);
        // Cria Set de veículos
        Set<Veiculo> veiculos = new HashSet<>();
        // Adiciona veículos na lista
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        // Vincula veículos ao propríetário
        proprietario.setVeiculos(veiculos);
        // Salva os registros
        var proprietarioSalvo = proprietarioService.save(proprietario);
        veiculoService.saveAll(veiculos);
        // Executa o método a ser testado
        var veiculosEncontrados = veiculoService.findAllByIdProprietario(proprietarioSalvo.getId());
        assertEquals(veiculos.size(), veiculosEncontrados.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoProprietarioNaoTiverVeiculosCadastrados() {
        var proprietario = new Proprietario();
        proprietario.setNome("proprietario10");
        var proprietarioSalvo = proprietarioService.save(proprietario);
        // Executa o método a ser testado
        var veiculosEncontrados = veiculoService.findAllByIdProprietario(proprietarioSalvo.getId());
        assertTrue(veiculosEncontrados.isEmpty());
    }

//    @Test
//    void deveLancarExceptionQuandoIdForNulo() {
//        var veiculoInput = new VeiculoInputMapper();
//        assertThrows(InvalidDataAccessApiUsageException.class, () -> veiculoService.selecionaComoPrincipal(veiculoInput));
//    }

//    @Test
//    void deveLancarExceptionQuandoNaoForEncontradoVeiculoPeloId() {
//        var veiculoInput = new VeiculoInputMapper();
//        veiculoInput.setId(10L);
//        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.selecionaComoPrincipal(veiculoInput));
//    }

//    @Test
//    void deveSelecionarVeiculoComoPrincipalQuandoVeiculoNaoEstiverComStatusPrincipal() {
//        // Cria veículo
//        var veiculo = new Veiculo("placa1", "Marca1", "Modelo1");
//        // Salva e retorna veículo criado
//        var veiculoCriado = veiculoService.save(veiculo);
//        // Cria VeiculoInputMapper
//        var veiculoInput = new VeiculoInputMapper();
//        // Adiciona id do veículo criado ao objeto
//        veiculoInput.setId(veiculoCriado.getId());
//        // Executa método a ser testado
//        veiculoService.selecionaComoPrincipal(veiculoInput);
//        var veiculoAlterado = veiculoService.findById(veiculoInput.getId());
//        // Deve alterar o veículo para principal
//        assertNotEquals(veiculoCriado.isPrincipal(), veiculoAlterado.isPrincipal());
//    }

//    @Test
//    void deveDesmarcarVeiculoPrincipalCriadoQuandoInformadoOutroVeiculoComoPrincipal() {
//        // Cria veículo
//        var veiculoPrincipalAtual = new Veiculo("placa1", "Marca1", "Modelo1");
//        var veiculoParametro = new Veiculo("placa2", "Marca2", "Modelo2");
//        // Seta veículo1 como principal
//        veiculoPrincipalAtual.setPrincipal(true);
//        // Salva e retorna veículo criado
//        var veiculoCriadoPrincipalAtual = veiculoService.save(veiculoPrincipalAtual);
//        var veiculoCriadoParametro = veiculoService.save(veiculoParametro);
//        // Cria VeiculoInputMapper
//        var veiculoInput = new VeiculoInputMapper();
//        // Adiciona id do veículo criado ao objeto
//        veiculoInput.setId(veiculoCriadoParametro.getId());
//        // Executa método a ser testado
//        veiculoService.selecionaComoPrincipal(veiculoInput);
//        var veiculoAlterado = veiculoService.findById(veiculoCriadoPrincipalAtual.getId());
//        // Deve alterar o veículo para não principal, pois foi executado o método selecionaComoPrincipal(),
//        // alterando o veículo informado no parâmetro para principal e alterando de principal para não principal
//        // qualquer outro veículo que não seja o recebido
//        assertFalse(veiculoAlterado.isPrincipal());
//    }

    @Test
    void deveRetornarVeiculoPrincipalQuandoProprietarioTiverVeiculoPrincipal() {
        var veiculoPrincipalAtual = new Veiculo("placa1", "Marca1", "Modelo1");
        veiculoPrincipalAtual.setPrincipal(true);
        // Cria o proprietário
        var proprietario = new Proprietario();
        proprietario.setNome("proprietario");
        // Retorna proprietário salvo
        var proprietarioCriado = proprietarioService.save(proprietario);
        // Vincula proprietario ao veículo
        veiculoPrincipalAtual.setProprietario(proprietario);
        // Retorna veículo principal salvo
        var veiculoCriadoPrincipalAtual = veiculoService.save(veiculoPrincipalAtual);
        // Retorna veículo encontrado
        var veiculoEncontrado = veiculoService.findVeiculoPrincipal(proprietarioCriado.getId());
        assertEquals(veiculoEncontrado.get().getId(), veiculoCriadoPrincipalAtual.getId());
    }

    @Test
    @Transactional
    void deveRetornarNuloQuandoProprietarioNaoTiverVeiculoPrincipal() {
        var veiculoPrincipalAtual = new Veiculo("placa1", "Marca1", "Modelo1");
        veiculoPrincipalAtual.setPrincipal(false);
        // Cria o proprietário
        var proprietario = new Proprietario();
        proprietario.setNome("proprietario");
        // Retorna proprietário salvo
        var proprietarioCriado = proprietarioService.save(proprietario);
        // Vincula proprietario ao veículo
        veiculoPrincipalAtual.setProprietario(proprietario);
        // Retorna veículo principal salvo
        var veiculoCriadoPrincipalAtual = veiculoService.save(veiculoPrincipalAtual);
        var veiculoEncontrado = veiculoService.findVeiculoPrincipal(proprietarioCriado.getId());
        assertEquals(Optional.empty(), veiculoEncontrado);
    }

    @Test
    void deveRetornarVeiculoSalvoComIdPreenchido() {
        var veiculoPrincipalAtual = new Veiculo("placa1", "Marca1", "Modelo1");
        var veiculoCriado = veiculoService.save(veiculoPrincipalAtual);
        assertNotNull(veiculoCriado.getId());
    }

    @Test
    void deveLancarExceptionQuandoAtributosObrigatoriosNaoForemInformadosNoVeiculo() {
        var veiculoPrincipalAtual = new Veiculo();
        assertThrows(ConstraintViolationException.class, () -> veiculoService.save(veiculoPrincipalAtual));
    }

}