package sv.edu.udb.service;

import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
    Optional<Orden> findById(Integer id);
}
