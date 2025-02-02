package jojolete.jojolete.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jojolete.jojolete.models.Categoria;
import jojolete.jojolete.service.CategoriaServicio;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")  // Permitir todas las conexiones (ajustar según sea necesario)
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    // Endpoint para obtener todas las categorías
    @GetMapping("/all")
    public List<Categoria> getAllCategorias() {
        return categoriaServicio.getAllCategorias();
    }

    // Endpoint para guardar una categoría
    @PostMapping("/save")
    public String saveCategoria(@RequestBody Categoria categoria) {
        return categoriaServicio.saveCategoria(categoria);
    }

    // Endpoint para actualizar una categoría
    @PutMapping("/update/{id}")
    public String updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaServicio.updateCategoria(id, categoria);
    }

    // Endpoint para eliminar una categoría
    @DeleteMapping("/delete/{id}")
    public String deleteCategoria(@PathVariable Long id) {
        return categoriaServicio.deleteCategoria(id);
    }

    // Endpoint para buscar una categoría por id
    @GetMapping("/find/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaServicio.getCategoriaById(id);
    }
}
