package jojolete.jojolete.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "cabecera_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "mesera")
    private String Mesera;
    
    @Column(nullable = false)
    private String fecha;
    
    @Column(nullable = false)
    private Double total;
    
    @OneToMany(mappedBy = "cabeceraVenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetalleVenta> detalles;
}
