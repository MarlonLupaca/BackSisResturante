package jojolete.jojolete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jojolete.jojolete.models.Producto;
import jojolete.jojolete.repositorios.ProductoRepositorio;
@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }

    public String saveProducto(Producto producto) {
        try {
            productoRepositorio.save(producto);
            return "Producto guardado exitosamente";
        } catch (Exception e) {
            return "Error al guardar el producto: " + e.getMessage();
        }
    }

    public String updateProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoOptional = productoRepositorio.findById(id);

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            
            try {
                productoRepositorio.save(producto);
                return "Producto actualizado exitosamente";
            } catch (Exception e) {
                return "Error al actualizar el producto: " + e.getMessage();
            }
        } else {
            return "Producto no encontrado";
        }
    }

    public String deleteProducto(Long id) {
        Optional<Producto> productoOptional = productoRepositorio.findById(id);

        if (productoOptional.isPresent()) {
            try {
                productoRepositorio.delete(productoOptional.get());
                return "Producto eliminado exitosamente";
            } catch (Exception e) {
                return "Error al eliminar el producto: " + e.getMessage();
            }
        } else {
            return "Producto no encontrado";
        }
    }

    public Producto getProductoById(Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }
}
