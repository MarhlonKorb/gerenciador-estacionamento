package marhlonkorb.github.io.gerenciadorestacionamento.core;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import marhlonkorb.github.io.gerenciadorestacionamento.core.abstractentities.entidadecomid.EntidadeComId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class AbstractEntityService<EntidadeComId, ID, Input, DtoType> {

    @Autowired
    protected JpaRepository<EntidadeComId, ID> repository;

    @Autowired
    private AbstractEntityMapper<EntidadeComId, Input, DtoType> abstractEntityMapper;

    public List<Object> getAll() {
        List<EntidadeComId> entities = repository.findAll();
        return entities.stream()
                .map(abstractEntityMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public DtoType getById(ID id) {
        EntidadeComId entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar a entidade com o ID " + id));
        return abstractEntityMapper.convertToDto(entity);
    }

    public Page<DtoType> getPageable(Pageable pageable) {
        Page<EntidadeComId> entitiesPage = repository.findAll(pageable);
        return entitiesPage.map(abstractEntityMapper::convertToDto);
    }

    @Transactional
    public DtoType create(Input input) {
        final var convertedInput = abstractEntityMapper.convertToEntity(input);
        EntidadeComId savedEntity = repository.save(convertedInput);
        return abstractEntityMapper.convertToDto(savedEntity);
    }

    @Transactional
    public DtoType update(ID id, Input input) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possível encontrar a entidade com o ID " + id);
        }
        final var entityConverted = abstractEntityMapper.convertToEntity(input);
        EntidadeComId savedEntity = repository.save(entityConverted);
        return abstractEntityMapper.convertToDto(savedEntity);
    }

    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possível excluir a entidade com o ID " + id + ".");
        }
        repository.deleteById(id);
    }


}
