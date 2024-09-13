package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.DetalleOrden;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Usuario;

import java.util.List;

@Repository
@Transactional
public class DetalleOrdenRepository {
    @PersistenceContext
    private EntityManager em;
    public List<DetalleOrden> findAll() {
        return em.createQuery("select d from DetalleOrden d", DetalleOrden.class).getResultList();
    }
    public void save(DetalleOrden detalleOrden) {
        em.persist(detalleOrden);
    }
    public void delete(DetalleOrden detalleOrden) {
        em.remove(detalleOrden);
    }
    public DetalleOrden findById(int id) {
        return em.find(DetalleOrden.class, id);
    }
    public Usuario usuarioFK (int id) {
        return em.getReference(Usuario.class, id);
    }
    public Orden ordenFK (int id) {
        return em.getReference(Orden.class, id);
    }
}
