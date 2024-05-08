package marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma entidade padrão
 */
@MappedSuperclass
public abstract class EntidadeComId implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
