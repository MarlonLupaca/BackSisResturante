package jojolete.jojolete.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jojolete.jojolete.models.Ubicacion;
import jojolete.jojolete.service.UbicacionServicio;

@RestController
@RequestMapping("/ubicacion")
@CrossOrigin(origins = "*")  // Permitir todas las conexiones (ajustar según sea necesario)
public class UbicacionControlador {

    @Autowired
    private UbicacionServicio ubicacionServicio;

    // Endpoint para obtener todas las ubicaciones
    @GetMapping("/all")
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionServicio.getAllUbicaciones();
    }

    // Endpoint para guardar una ubicación
    @PostMapping("/save")
    public String saveUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionServicio.saveUbicacion(ubicacion);
    }

    // Endpoint para actualizar una ubicación
    @PutMapping("/update/{id}")
    public String updateUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        return ubicacionServicio.updateUbicacion(id, ubicacion);
    }

    // Endpoint para eliminar una ubicación
    @DeleteMapping("/delete/{id}")
    public String deleteUbicacion(@PathVariable Long id) {
        return ubicacionServicio.deleteUbicacion(id);
    }

    // Endpoint para buscar una ubicación por id
    @GetMapping("/find/{id}")
    public Ubicacion getUbicacionById(@PathVariable Long id) {
        return ubicacionServicio.getUbicacionById(id);
    }
}
