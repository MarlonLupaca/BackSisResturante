package jojolete.jojolete.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jojolete.jojolete.models.Medida;
import jojolete.jojolete.service.MedidaServicio;

@RestController
@RequestMapping("/medida")
@CrossOrigin(origins = "*")
public class MedidaControlador {
    
    @Autowired
    private MedidaServicio medidaServicio;

    // Endpoint para obtener todas las medidas
    @GetMapping("/all")
    public List<Medida> getAllMedidas() {
        return medidaServicio.getAllMedidas();
    }

    // Endpoint para guardar una medida
    @PostMapping("/save")
    public String saveMedida(@RequestBody Medida medida) {
        return medidaServicio.saveMedida(medida);
    }

    // Endpoint para actualizar una medida
    @PutMapping("/update/{id}")
    public String updateMedida(@PathVariable Long id, @RequestBody Medida medida) {
        return medidaServicio.updateMedida(id, medida);
    }

    // Endpoint para eliminar una medida
    @DeleteMapping("/delete/{id}")
    public String deleteMedida(@PathVariable Long id) {
        return medidaServicio.deleteMedida(id);
    }

    // Endpoint para buscar una medida por id
    @GetMapping("/find/{id}")
    public Medida getMedidaById(@PathVariable Long id) {
        return medidaServicio.getMedidaById(id);
    }
}
