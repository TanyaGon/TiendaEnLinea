package sv.edu.udb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "detalles")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;
    @ManyToOne
    private Orden orden;
    @ManyToOne
    private Producto producto;
}
