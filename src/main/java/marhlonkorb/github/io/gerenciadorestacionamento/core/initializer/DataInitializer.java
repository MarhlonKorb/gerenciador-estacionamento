package marhlonkorb.github.io.gerenciadorestacionamento.core.initializer;

import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.Status;
import marhlonkorb.github.io.gerenciadorestacionamento.core.enums.StatusVaga;
import marhlonkorb.github.io.gerenciadorestacionamento.models.entities.vaga.Vaga;
import marhlonkorb.github.io.gerenciadorestacionamento.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VagaRepository vagaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (vagaRepository.findById(1L).isEmpty()) {
            for (int i = 0; i < 200; i++) {
                // Crie um novo registro com os dados desejados
                Vaga vaga = new Vaga(StatusVaga.L);
                // Executa a inserção dos registros
                vaga.setStatus(Status.A);
                vagaRepository.save(vaga);
            }
        }
    }
}