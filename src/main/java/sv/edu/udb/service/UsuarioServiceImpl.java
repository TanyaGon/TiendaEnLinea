package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {
    final private IUsuarioRepository repository;

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> findbyEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }
}
