package jojolete.jojolete.service;

import jojolete.jojolete.dto.DetalleMesaDTO;
import jojolete.jojolete.models.Mesa;
import jojolete.jojolete.models.Plato;
import jojolete.jojolete.models.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import jojolete.jojolete.repositorios.DetalleMesaRepository;
import jojolete.jojolete.repositorios.MesaRepository;
import jojolete.jojolete.repositorios.PlatoRepositorio;
import jojolete.jojolete.repositorios.ProductoRepositorio;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;
    private final DetalleMesaRepository detalleMesaRepository;
    private final PlatoRepositorio platoRepository;
    private final ProductoRepositorio productoRepository;

    @Transactional
    public Mesa crearMesa(Mesa mesa) {
        if (mesaRepository.existsByNombre(mesa.getNombre())) {
            throw new RuntimeException("Ya existe una mesa con este nombre");
        }
        return mesaRepository.save(mesa);
    }

    @Transactional(readOnly = true)
    public List<Mesa> obtenerTodasLasMesas() {
        return mesaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mesa obtenerMesaPorNombre(String nombre) {
        return mesaRepository.findByNombre(nombre);
    }

    @Transactional
    public DetalleMesaDTO agregarDetalle(String mesaNombre, Long platoId, Long productoId, Integer cantidad, Double subtotal) {
        Mesa mesa = mesaRepository.findByNombre(mesaNombre);
        if (mesa == null) {
            throw new RuntimeException("Mesa no encontrada");
        }

        // Validación: solo se puede agregar plato O producto, no ambos
        if ((platoId == null && productoId == null) || (platoId != null && productoId != null)) {
            throw new RuntimeException("Debe especificar exactamente un plato o un producto");
        }

        Plato plato = platoId != null ? platoRepository.findById(platoId)
            .orElseThrow(() -> new RuntimeException("Plato no encontrado")) : null;
        Producto producto = productoId != null ? productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")) : null;

        DetalleMesaDTO detalle = new DetalleMesaDTO();
        detalle.setMesa(mesa);
        detalle.setPlato(plato);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(subtotal);

        return detalleMesaRepository.save(detalle);
    }

    @Transactional
    public void eliminarDetalle(Long detalleId) {
        detalleMesaRepository.deleteById(detalleId);
    }

    @Transactional
    public void eliminarMesa(String nombre) {
        Mesa mesa = mesaRepository.findByNombre(nombre);
        if (mesa != null) {
            detalleMesaRepository.deleteByMesa_Nombre(nombre);
            mesaRepository.delete(mesa);
        }
    }

    @Transactional(readOnly = true)
    public List<DetalleMesaDTO> obtenerDetallesPorMesa(String mesaNombre) {
        return detalleMesaRepository.findByMesa_Nombre(mesaNombre);
    }
    
    @Transactional
    public DetalleMesaDTO actualizarCantidadDetalle(Long detalleId, Integer nuevaCantidad) {
        DetalleMesaDTO detalle = detalleMesaRepository.findById(detalleId)
            .orElseThrow(() -> new RuntimeException("Detalle de mesa no encontrado"));

        // Validación de cantidad (opcional, ajusta según tus requisitos)
        if (nuevaCantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor que cero");
        }

        // Actualizar la cantidad
        detalle.setCantidad(nuevaCantidad);

        // Recalcular subtotal si es necesario (dependiendo de cómo calcules el subtotal)
        if (detalle.getPlato() != null) {
            detalle.setSubtotal(detalle.getPlato().getPrecio() * nuevaCantidad);
        } else if (detalle.getProducto() != null) {
            detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);
        }

        // Guardar y devolver el detalle actualizado
        return detalleMesaRepository.save(detalle);
    }
    
    @Transactional
    public void eliminarDetallesPorNombreMesa(String nombreMesa) {
        // Verificar si la mesa existe
        Mesa mesa = mesaRepository.findByNombre(nombreMesa);
        if (mesa == null) {
            throw new RuntimeException("Mesa no encontrada");
        }

        // Eliminar todos los detalles asociados a la mesa
        detalleMesaRepository.deleteByMesa_Nombre(nombreMesa);
    }
}