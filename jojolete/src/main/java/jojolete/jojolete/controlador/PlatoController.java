package jojolete.jojolete.controlador;


import jojolete.jojolete.models.Plato;
import jojolete.jojolete.service.PlatoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
@CrossOrigin(origins = "*")
public class PlatoController {
    @Autowired
    private PlatoServicio platoServicio;

    @GetMapping
    public List<Plato> getAllPlatos() {
        return platoServicio.getAllPlatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Long id) {
        Plato plato = platoServicio.getPlatoById(id);
        return plato != null ? ResponseEntity.ok(plato) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> savePlato(@RequestBody Plato plato) {
        return ResponseEntity.ok(platoServicio.savePlato(plato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlato(@PathVariable Long id, @RequestBody Plato plato) {
        return ResponseEntity.ok(platoServicio.updatePlato(id, plato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlato(@PathVariable Long id) {
        return ResponseEntity.ok(platoServicio.deletePlato(id));
    }
}