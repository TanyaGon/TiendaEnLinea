package sv.edu.udb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private double total;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;
}
