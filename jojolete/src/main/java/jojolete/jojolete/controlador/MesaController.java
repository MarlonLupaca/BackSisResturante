package jojolete.jojolete.controlador;

import jojolete.jojolete.dto.DetalleMesaDTO;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MesaController {
    private final MesaService mesaService;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Mesa> crearMesa(@RequestBody Mesa mesa) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        return ResponseEntity.ok(mesaService.crearMesa(mesa));
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> obtenerTodasLasMesas() {
        return ResponseEntity.ok(mesaService.obtenerTodasLasMesas());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Mesa> obtenerMesaPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(mesaService.obtenerMesaPorNombre(nombre));
    }

    @PostMapping("/{mesaNombre}/detalles")
    public ResponseEntity<DetalleMesaDTO> agregarDetalle(
            @PathVariable String mesaNombre,
            @RequestBody DetalleRequest detalleRequest) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        return ResponseEntity.ok(mesaService.agregarDetalle(
            mesaNombre, 
            detalleRequest.getPlatoId(), 
            detalleRequest.getProductoId(), 
            detalleRequest.getCantidad(), 
            detalleRequest.getSubtotal()
        ));
    }

    @DeleteMapping("/detalles/{detalleId}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long detalleId) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        mesaService.eliminarDetalle(detalleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable String nombre) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        mesaService.eliminarMesa(nombre);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{mesaNombre}/detalles")
    public ResponseEntity<List<DetalleMesaDTO>> obtenerDetallesPorMesa(@PathVariable String mesaNombre) {
        return ResponseEntity.ok(mesaService.obtenerDetallesPorMesa(mesaNombre));
    }
    // Nuevo método con path variable
    @PutMapping("/detalles/{detalleId}/{cantidad}")
    public ResponseEntity<DetalleMesaDTO> actualizarCantidadDetalle(
        @PathVariable Long detalleId, 
        @PathVariable Integer cantidad) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        DetalleMesaDTO detalleActualizado = mesaService.actualizarCantidadDetalle(detalleId, cantidad);
        return ResponseEntity.ok(detalleActualizado);
    }
    @DeleteMapping("/{nombreMesa}/detalles")
    public ResponseEntity<Void> eliminarDetallesMesa(@PathVariable String nombreMesa) {
        messagingTemplate.convertAndSend("/topic/mesas", "{ \"evento\": \"ELIMINADO\", \"nombre\": \"" + "\" }");
        mesaService.eliminarDetallesPorNombreMesa(nombreMesa);
        return ResponseEntity.ok().build();
    }

    // DTO para manejar la solicitud de agregar detalle
    public static class DetalleRequest {
        private Long platoId;
        private Long productoId;
        private Integer cantidad;
        private Double subtotal;

        // Getters y setters
        public Long getPlatoId() {
            return platoId;
        }

        public void setPlatoId(Long platoId) {
            this.platoId = platoId;
        }

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
    }
}