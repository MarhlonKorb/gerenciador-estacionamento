package marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Classe abstrata que representa uma entidade padrão
 */
@MappedSuperclass
public abstract class EntidadeComId implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
