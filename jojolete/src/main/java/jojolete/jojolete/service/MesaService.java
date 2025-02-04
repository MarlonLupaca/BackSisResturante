package jojolete.jojolete.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.repositorios.MesaRepository;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;

    public Mesa guardarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public List<Mesa> obtenerTodasLasMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obtenerMesaPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public void eliminarMesa(Long id) {
        mesaRepository.deleteById(id);
    }
}
