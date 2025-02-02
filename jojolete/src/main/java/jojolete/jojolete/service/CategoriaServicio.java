package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Categoria;
import jojolete.jojolete.repositorios.CategoriaRepositorio;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    // Obtener todas las categorías
    public List<Categoria> getAllCategorias() {
        return categoriaRepositorio.findAll();
    }

    // Guardar una categoría
    public String saveCategoria(Categoria categoria) {
        try {
            categoriaRepositorio.save(categoria);
            return "Categoría guardada exitosamente";
        } catch (Exception e) {
            return "Error al guardar la categoría: " + e.getMessage();
        }
    }

    // Actualizar una categoría por ID
    public String updateCategoria(Long id, Categoria categoriaActualizada) {
        Optional<Categoria> categoriaOptional = categoriaRepositorio.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();

            // Actualizar campos de la categoría
            categoria.setNombre(categoriaActualizada.getNombre());
            categoria.setComentario(categoriaActualizada.getComentario());

            try {
                categoriaRepositorio.save(categoria);
                return "Categoría actualizada exitosamente";
            } catch (Exception e) {
                return "Error al actualizar la categoría: " + e.getMessage();
            }
        } else {
            return "Categoría no encontrada";
        }
    }

    // Eliminar una categoría por ID
    public String deleteCategoria(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepositorio.findById(id);

        if (categoriaOptional.isPresent()) {
            try {
                categoriaRepositorio.delete(categoriaOptional.get());
                return "Categoría eliminada exitosamente";
            } catch (Exception e) {
                return "Error al eliminar la categoría: " + e.getMessage();
            }
        } else {
            return "Categoría no encontrada";
        }
    }

    // Buscar categoría por ID
    public Categoria getCategoriaById(Long id) {
        return categoriaRepositorio.findById(id).orElse(null);
    }
}
