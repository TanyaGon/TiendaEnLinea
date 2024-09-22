package sv.edu.udb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.model.DetalleOrden;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.DetalleOrdenRepository;

@SpringBootTest
public class DetalleOrdenRepositoryTest {
    @Autowired
    DetalleOrdenRepository detalleOrdenRepository;
    @Test
    void insertDetalleOrden() {
        String nombre = "Acetaminofen";
        double cantidad = 3.0;
        double precio =  1.00;
        double total = 3.00;
        DetalleOrden detalleOrden = DetalleOrden
                .builder()
                .nombre(nombre)
                .cantidad(cantidad)
                .precio(precio)
                .total(total)
                .build();
        detalleOrdenRepository.save(detalleOrden);
        System.out.println("DetalleOrden insertado correctamente");
    }
    @Test
    void findUserById(){
        int id = 1;
        DetalleOrden detalleOrden = detalleOrdenRepository.findById(id);
        System.out.println("DetalleOrden encontrado");
        System.out.println(detalleOrden.toString());
    }
}
