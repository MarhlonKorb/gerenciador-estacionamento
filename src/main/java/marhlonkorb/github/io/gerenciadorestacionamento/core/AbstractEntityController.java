package marhlonkorb.github.io.gerenciadorestacionamento.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractEntityController<T, ID, Input, DtoType> {

    @Autowired
    protected AbstractEntityService<T, ID, Input, DtoType> service;

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
