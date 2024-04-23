package marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadeauditada;

import jakarta.persistence.*;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid.EntidadeComId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

/**
 * Classe abstrata que representa uma entidade auditada
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntidadeAuditada extends EntidadeComId {

    @Column
    @CreatedBy
    protected String criadoPor;
    @Column
    @LastModifiedBy
    private String alteradoPor;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataCriacao;

    @Column
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAlteracao;

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public String getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(String alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
