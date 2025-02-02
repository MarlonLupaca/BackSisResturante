package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Proveedor;
import jojolete.jojolete.repositorios.ProveedorRepositorio;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    // Obtener todos los proveedores
    public List<Proveedor> getAllProveedores() {
        return proveedorRepositorio.findAll();
    }

    // Guardar un proveedor
    public String saveProveedor(Proveedor proveedor) {
        try {
            proveedorRepositorio.save(proveedor);
            return "Proveedor guardado exitosamente";
        } catch (Exception e) {
            return "Error al guardar el proveedor: " + e.getMessage();
        }
    }

    // Actualizar un proveedor por ID
    public String updateProveedor(Long id, Proveedor proveedorActualizado) {
        Optional<Proveedor> proveedorOptional = proveedorRepositorio.findById(id);

        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();

            // Actualizar campos del proveedor
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setComentario(proveedorActualizado.getComentario());

            try {
                proveedorRepositorio.save(proveedor);
                return "Proveedor actualizado exitosamente";
            } catch (Exception e) {
                return "Error al actualizar el proveedor: " + e.getMessage();
            }
        } else {
            return "Proveedor no encontrado";
        }
    }

    // Eliminar un proveedor por ID
    public String deleteProveedor(Long id) {
        Optional<Proveedor> proveedorOptional = proveedorRepositorio.findById(id);

        if (proveedorOptional.isPresent()) {
            try {
                proveedorRepositorio.delete(proveedorOptional.get());
                return "Proveedor eliminado exitosamente";
            } catch (Exception e) {
                return "Error al eliminar el proveedor: " + e.getMessage();
            }
        } else {
            return "Proveedor no encontrado";
        }
    }

    // Buscar proveedor por ID
    public Proveedor getProveedorById(Long id) {
        return proveedorRepositorio.findById(id).orElse(null);
    }
}
