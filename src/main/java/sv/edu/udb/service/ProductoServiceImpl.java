package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.IProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {
    final private IProductoRepository repository;

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }
}