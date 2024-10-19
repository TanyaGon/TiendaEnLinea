package sv.edu.udb.service;

import sv.edu.udb.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findbyEmail(String email);
    List<Usuario> findAll();
}
