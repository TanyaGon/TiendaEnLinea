package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Usuario;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuario(Usuario usuario);
}
