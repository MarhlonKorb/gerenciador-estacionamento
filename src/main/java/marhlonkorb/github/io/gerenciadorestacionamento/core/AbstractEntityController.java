package marhlonkorb.github.io.gerenciadorestacionamento.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class AbstractEntityController<T, ID, Input, DtoType> {
    @Autowired
    AbstractEntityService<T, ID, Input, DtoType> service;

    @GetMapping("/all")
    public List<Object> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    public DtoType getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<DtoType> listEntities(Pageable pageable) {
        return service.getPageable(pageable);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public DtoType create(@RequestBody Input input) {
        return service.create(input);
    }

    @PutMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public DtoType update(@PathVariable ID id, @RequestBody Input input) {
        return service.update(id, input);
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }
}
