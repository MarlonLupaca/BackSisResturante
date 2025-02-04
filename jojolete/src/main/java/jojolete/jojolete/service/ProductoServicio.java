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

    // Método para obtener solo los productos activos
    public List<Producto> getAllProductos() {
        return productoRepositorio.findByEstadoTrue();  // Método customizado en el repositorio
    }

    // Método para actualizar el estado (activo/inactivo) de un producto
    public String updateEstadoProducto(Long id, boolean estado) {
        Optional<Producto> productoOptional = productoRepositorio.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setEstado(estado); // Cambiar el estado del producto
            
            try {
                productoRepositorio.save(producto);
                return "Estado del producto actualizado exitosamente";
            } catch (Exception e) {
                return "Error al actualizar el estado del producto: " + e.getMessage();
            }
        } else {
            return "Producto no encontrado";
        }
    }

    // Nuevo método para obtener productos con stock bajo
    public List<Producto> getProductosStockBajo() {
        return productoRepositorio.findByStockLessThan(10);
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
