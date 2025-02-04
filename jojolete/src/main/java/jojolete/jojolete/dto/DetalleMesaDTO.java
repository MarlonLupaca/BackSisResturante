package jojolete.jojolete.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.models.Plato;
import jojolete.jojolete.models.Producto;


public class DetalleMesaDTO {
    
   
    
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
