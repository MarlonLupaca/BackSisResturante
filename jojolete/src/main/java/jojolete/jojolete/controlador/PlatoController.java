package jojolete.jojolete.controlador;


import jojolete.jojolete.models.Plato;
import jojolete.jojolete.service.PlatoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@RestController
@RequestMapping("/platos")
@CrossOrigin(origins = "*")
public class PlatoController {
    @Autowired
    private PlatoServicio platoServicio;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

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
        platoServicio.savePlato(plato);
        messagingTemplate.convertAndSend("/topic/platos", "{ \"evento\": \"CREADO\", \"nombre\": \"" + plato.getNombre() + "\" }");
        return ResponseEntity.ok("Plato creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlato(@PathVariable Long id, @RequestBody Plato plato) {
        platoServicio.updatePlato(id, plato);
        messagingTemplate.convertAndSend("/topic/platos", "{ \"evento\": \"ACTUALIZADO\", \"id\": " + id + " }");
        return ResponseEntity.ok("Plato actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlato(@PathVariable Long id) {
        platoServicio.deletePlato(id);
        messagingTemplate.convertAndSend("/topic/platos", "{ \"evento\": \"ELIMINADO\", \"id\": " + id + " }");
        return ResponseEntity.ok("Plato eliminado");
    }
    
    @PutMapping("/estado/{id}")
    public String updateEstadoPlato(@PathVariable Long id, @RequestParam boolean estado) {
        return platoServicio.updateEstadoPlato(id, estado);
    }

}