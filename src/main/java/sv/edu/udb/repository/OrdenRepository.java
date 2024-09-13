package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Usuario;

import java.util.List;

@Repository
@Transactional
public class OrdenRepository {
    @PersistenceContext
    private EntityManager em;
    public List<Orden> listarOrdenes(){
        return em.createNamedQuery("Orden.findAll", Orden.class).getResultList();
    }
    public void save(Orden orden){
        em.persist(orden);
    }
    public void delete(Orden orden){
        em.remove(orden);
    }
    public Orden findById(int id){
        return em.find(Orden.class, id);
    }
    public Usuario usuarioFK(int id){
        return em.getReference(Usuario.class, id);
    }
}
