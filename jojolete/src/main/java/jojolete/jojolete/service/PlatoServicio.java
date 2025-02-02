package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Plato;
import jojolete.jojolete.repositorios.PlatoRepositorio;

@Service
public class PlatoServicio {
    
    @Autowired
    private PlatoRepositorio platoRepositorio;

    public List<Plato> getAllPlatos() {
        return platoRepositorio.findAll();
    }

    public String savePlato(Plato plato) {
        try {
            platoRepositorio.save(plato);
            return "Plato guardado exitosamente";
        } catch (Exception e) {
            return "Error al guardar el plato: " + e.getMessage();
        }
    }

    public String updatePlato(Long id, Plato platoActualizado) {
        Optional<Plato> platoOptional = platoRepositorio.findById(id);

        if (platoOptional.isPresent()) {
            Plato plato = platoOptional.get();
            plato.setNombre(platoActualizado.getNombre());
            plato.setPrecio(platoActualizado.getPrecio());
            
            try {
                platoRepositorio.save(plato);
                return "Plato actualizado exitosamente";
            } catch (Exception e) {
                return "Error al actualizar el plato: " + e.getMessage();
            }
        } else {
            return "Plato no encontrado";
        }
    }

    public String deletePlato(Long id) {
        Optional<Plato> platoOptional = platoRepositorio.findById(id);

        if (platoOptional.isPresent()) {
            try {
                platoRepositorio.delete(platoOptional.get());
                return "Plato eliminado exitosamente";
            } catch (Exception e) {
                return "Error al eliminar el plato: " + e.getMessage();
            }
        } else {
            return "Plato no encontrado";
        }
    }

    public Plato getPlatoById(Long id) {
        return platoRepositorio.findById(id).orElse(null);
    }
}