package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Ubicacion;
import jojolete.jojolete.repositorios.UbicacionRepositorio;

@Service
public class UbicacionServicio {

    @Autowired
    private UbicacionRepositorio ubicacionRepositorio;

    // Obtener todas las ubicaciones
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepositorio.findAll();
    }

    // Guardar una ubicación
    public String saveUbicacion(Ubicacion ubicacion) {
        try {
            ubicacionRepositorio.save(ubicacion);
            return "Ubicación guardada exitosamente";
        } catch (Exception e) {
            return "Error al guardar la ubicación: " + e.getMessage();
        }
    }

    // Actualizar una ubicación por ID
    public String updateUbicacion(Long id, Ubicacion ubicacionActualizada) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepositorio.findById(id);

        if (ubicacionOptional.isPresent()) {
            Ubicacion ubicacion = ubicacionOptional.get();

            // Actualizar campos de la ubicación
            ubicacion.setNombre(ubicacionActualizada.getNombre());
            ubicacion.setComentario(ubicacionActualizada.getComentario());

            try {
                ubicacionRepositorio.save(ubicacion);
                return "Ubicación actualizada exitosamente";
            } catch (Exception e) {
                return "Error al actualizar la ubicación: " + e.getMessage();
            }
        } else {
            return "Ubicación no encontrada";
        }
    }

    // Eliminar una ubicación por ID
    public String deleteUbicacion(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepositorio.findById(id);

        if (ubicacionOptional.isPresent()) {
            try {
                ubicacionRepositorio.delete(ubicacionOptional.get());
                return "Ubicación eliminada exitosamente";
            } catch (Exception e) {
                return "Error al eliminar la ubicación: " + e.getMessage();
            }
        } else {
            return "Ubicación no encontrada";
        }
    }

    // Buscar ubicación por ID
    public Ubicacion getUbicacionById(Long id) {
        return ubicacionRepositorio.findById(id).orElse(null);
    }
}
