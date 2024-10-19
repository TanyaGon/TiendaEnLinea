package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.DetalleOrden;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}
