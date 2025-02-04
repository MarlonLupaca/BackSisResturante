package jojolete.jojolete.controlador;

import jojolete.jojolete.models.Producto;
import jojolete.jojolete.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    private ProductoServicio productoServicio;
    

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoServicio.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoServicio.getProductoById(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> saveProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoServicio.saveProducto(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoServicio.updateProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoServicio.deleteProducto(id));
    }
    @GetMapping("/stock-bajo")
    public List<Producto> getProductosStockBajo() {
        return productoServicio.getProductosStockBajo();
    }
    // Endpoint para actualizar el estado del producto (activo/inactivo)
    @PutMapping("/estado/{id}")
    public String updateEstadoProducto(@PathVariable Long id, @RequestParam boolean estado) {
        return productoServicio.updateEstadoProducto(id, estado);
    }
}
