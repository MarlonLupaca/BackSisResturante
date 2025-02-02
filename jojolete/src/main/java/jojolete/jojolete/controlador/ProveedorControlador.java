package jojolete.jojolete.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jojolete.jojolete.models.Proveedor;
import jojolete.jojolete.service.ProveedorServicio;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*")  // Permitir todas las conexiones (ajustar según sea necesario)
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;

    // Endpoint para obtener todos los proveedores
    @GetMapping("/all")
    public List<Proveedor> getAllProveedores() {
        return proveedorServicio.getAllProveedores();
    }

    // Endpoint para guardar un proveedor
    @PostMapping("/save")
    public String saveProveedor(@RequestBody Proveedor proveedor) {
        return proveedorServicio.saveProveedor(proveedor);
    }

    // Endpoint para actualizar un proveedor
    @PutMapping("/update/{id}")
    public String updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorServicio.updateProveedor(id, proveedor);
    }

    // Endpoint para eliminar un proveedor
    @DeleteMapping("/delete/{id}")
    public String deleteProveedor(@PathVariable Long id) {
        return proveedorServicio.deleteProveedor(id);
    }

    // Endpoint para buscar un proveedor por id
    @GetMapping("/find/{id}")
    public Proveedor getProveedorById(@PathVariable Long id) {
        return proveedorServicio.getProveedorById(id);
    }
}
