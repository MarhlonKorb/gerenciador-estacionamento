package marhlonkorb.github.io.gerenciadorestacionamento.core;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class IGenericEntityService<EntidadeComId, ID, Input, DtoType> implements IGenericEntityMapper<EntidadeComId, Input, DtoType> {

    @Autowired
    private JpaRepository<EntidadeComId, ID> repository;

    public List<Object> getAll() {
        List<EntidadeComId> entities = repository.findAll();
        return  entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DtoType getById(ID id) {
        EntidadeComId entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar a entidade com o ID " + id));
        return (DtoType) convertToDto(entity);
    }

    public Page<DtoType> getPageable(Pageable pageable) {
        Page<EntidadeComId> entitiesPage = repository.findAll(pageable);
        return (Page<DtoType>) entitiesPage.map(this::convertToDto);
    }

    public DtoType create(Input input) {
        final var convertedInput = convertToEntity(input);
        EntidadeComId savedEntity = repository.save((EntidadeComId)convertedInput);
        return (DtoType) convertToDto(savedEntity);
    }

    public DtoType update(ID id, Input input) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possível encontrar a entidade com o ID " + id);
        }
        final var entityConverted = convertToEntity(input);
        EntidadeComId savedEntity =  repository.save((EntidadeComId) entityConverted);
        return (DtoType) convertToDto(savedEntity);
    }

    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possível excluir a entidade com o ID " + id + ".");
        }
        repository.deleteById(id);
    }

}