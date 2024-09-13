package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;

import java.util.List;

@Repository
@Transactional
public class ProductoRepository {

    @PersistenceContext
    private EntityManager em;
    public List<Producto> findAll() {
        return em.createNamedQuery("prodcuto.findAll", Producto.class).getResultList();
    }
    public void save(Producto producto) {
        em.persist(producto);
    }
    public void delete(Producto producto) {
        em.remove(em.merge(producto));
    }
    public Producto findById(int id) {
        return em.find(Producto.class, id);
    }

    public Usuario usuarioFK (int id) {
        return em.getReference(Usuario.class, id);
    }

}
