package jojolete.jojolete.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.models.Plato;
import jojolete.jojolete.models.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_mesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMesaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "mesa_nombre", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "plato_id")
    private Plato plato;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double subtotal;
}
