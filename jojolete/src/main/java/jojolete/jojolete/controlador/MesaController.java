package jojolete.jojolete.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.service.MesaService;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {
    private final MesaService mesaService;

    @PostMapping
    public Mesa crearMesa(@RequestBody Mesa mesa) {
        return mesaService.guardarMesa(mesa);
    }

    @GetMapping
    public List<Mesa> obtenerTodasLasMesas() {
        return mesaService.obtenerTodasLasMesas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtenerMesaPorId(@PathVariable Long id) {
        return mesaService.obtenerMesaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
        return ResponseEntity.noContent().build();
    }
}
