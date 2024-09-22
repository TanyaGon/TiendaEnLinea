package sv.edu.udb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.model.Orden;
import sv.edu.udb.repository.OrdenRepository;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class OrdenRepositoryTest {
    @Autowired
    private OrdenRepository ordenRepository;
    @Test
    void insertOrder() {
        String numero = "2";
        Date fechaCreacion = Date.from(Instant.now());
        Date fechaRecibida = Date.from(Instant.now());
        Double total = 12.50;
        Orden order = Orden
                .builder()
                .numero(numero)
                .fechaCreacion(fechaCreacion)
                .fechaRecibida(fechaRecibida)
                .total(total)
                .build();
        ordenRepository.save(order);
        System.out.println("Orden insertada correctamente");
    }
    @Test
    void findOrderById(){
        int id = 1;
        Orden orden = ordenRepository.findById(id);
        System.out.println("Orden encontrada");
        System.out.println(orden.toString());
    }

}
