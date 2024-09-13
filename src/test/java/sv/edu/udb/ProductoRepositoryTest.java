package sv.edu.udb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.ProductoRepository;

@SpringBootTest
public class ProductoRepositoryTest {
    @Autowired
    private ProductoRepository productoRepository;
    @Test
    void insertProduct() {
        String nombre = "Acetaminofen";
        String descripcion  = "Medicamento analgesico";
        String imagen  = "https://th.bing.com/th/id/OIP.5y5aDfDt-BVz0sKr_BxiwAHaHa?rs=1&pid=ImgDetMain";
        double precio  = 0.20;
        int cantidad = 30;
        Producto prodcuto = Producto
                .builder()
                    .nombre(nombre)
                    .descripcion(descripcion)
                    .imagen(imagen)
                    .precio(precio)
                    .cantidad(cantidad)
                .build();
        productoRepository.save(prodcuto);
        System.out.println("Producto insertado correctamente");
    }
    @Test
    void findUserById(){
        int id = 1;
        Producto producto = productoRepository.findById(id);
        System.out.println("Producto encontrado");
        System.out.println(producto.toString());
    }

}
