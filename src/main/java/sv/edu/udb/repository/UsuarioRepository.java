package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.Usuario;

import java.util.List;

@Repository
@Transactional
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;
    public List<Usuario> findAll() {
        return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }
    public void save(Usuario usuario) {
        em.persist(usuario);
    }
    public void delete(Usuario usuario) {
        em.remove(em.find(Usuario.class, usuario.getId()));
    }
    public Usuario findById(int id) {
        return em.find(Usuario.class, id);
    }
    public void update(Usuario usuario) {
        em.merge(usuario);
        em.clear();
    }
}

