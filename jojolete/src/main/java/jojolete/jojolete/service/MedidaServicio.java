package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Medida;
import jojolete.jojolete.repositorios.MedidaRepositorio;

@Service
public class MedidaServicio {

    @Autowired
    private MedidaRepositorio medidaRepositorio;

    // Obtener todas las medidas
    public List<Medida> getAllMedidas() {
        return medidaRepositorio.findAll();
    }

    // Guardar una medida
    public String saveMedida(Medida medida) {
        try {
            medidaRepositorio.save(medida);
            return "Medida guardada exitosamente";
        } catch (Exception e) {
            return "Error al guardar la medida: " + e.getMessage();
        }
    }

    // Actualizar una medida por ID
    public String updateMedida(Long id, Medida medidaActualizada) {
        Optional<Medida> medidaOptional = medidaRepositorio.findById(id);

        if (medidaOptional.isPresent()) {
            Medida medida = medidaOptional.get();

            // Actualizar campos de la medida
            medida.setNombre(medidaActualizada.getNombre());
            medida.setComentario(medidaActualizada.getComentario());

            try {
                medidaRepositorio.save(medida);
                return "Medida actualizada exitosamente";
            } catch (Exception e) {
                return "Error al actualizar la medida: " + e.getMessage();
            }
        } else {
            return "Medida no encontrada";
        }
    }

    // Eliminar una medida por ID
    public String deleteMedida(Long id) {
        Optional<Medida> medidaOptional = medidaRepositorio.findById(id);

        if (medidaOptional.isPresent()) {
            try {
                medidaRepositorio.delete(medidaOptional.get());
                return "Medida eliminada exitosamente";
            } catch (Exception e) {
                return "Error al eliminar la medida: " + e.getMessage();
            }
        } else {
            return "Medida no encontrada";
        }
    }

    // Buscar medida por ID
    public Medida getMedidaById(Long id) {
        return medidaRepositorio.findById(id).orElse(null);
    }
}
