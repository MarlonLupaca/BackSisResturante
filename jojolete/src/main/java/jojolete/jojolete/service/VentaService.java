package jojolete.jojolete.service;

import jojolete.jojolete.models.CabeceraVenta;
import jojolete.jojolete.models.DetalleVenta;
import jojolete.jojolete.models.Producto;
import jojolete.jojolete.repositorios.CabeceraVentaRepository;
import jojolete.jojolete.repositorios.DetalleVentaRepository;
import jojolete.jojolete.repositorios.ProductoRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentaService {
    private final CabeceraVentaRepository cabeceraVentaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepositorio productoRepository;

    public List<CabeceraVenta> obtenerTodasLasVentas() {
        return cabeceraVentaRepository.findAll();
    }
    
    @Transactional
    public CabeceraVenta registrarVenta(CabeceraVenta cabeceraVenta) {
        // Validar que los campos requeridos no sean nulos
        if (cabeceraVenta == null || 
            cabeceraVenta.getDetalles() == null || 
            cabeceraVenta.getFecha() == null ||
            cabeceraVenta.getTotal() == null) {
            throw new IllegalArgumentException("Todos los campos son requeridos");
        }

        // Verificar stock antes de procesar la venta
        for (DetalleVenta detalle : cabeceraVenta.getDetalles()) {
            if (detalle.getProducto() != null) {
                Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " 
                        + detalle.getProducto().getId()));

                if (producto.getStock() == null || producto.getStock() < detalle.getCantidad()) {
                    throw new IllegalStateException("Stock insuficiente para el producto: " 
                        + producto.getNombre() + ". Stock actual: " 
                        + producto.getStock() + ", Cantidad solicitada: " 
                        + detalle.getCantidad());
                }
            }
        }

        // Primero guardamos la cabecera
        CabeceraVenta nuevaVenta = new CabeceraVenta();
        nuevaVenta.setFecha(cabeceraVenta.getFecha());
        nuevaVenta.setTotal(cabeceraVenta.getTotal());
        nuevaVenta.setMesera(cabeceraVenta.getMesera());
        nuevaVenta.setDetalles(new ArrayList<>());
        
        // Guardamos la cabecera primero
        nuevaVenta = cabeceraVentaRepository.save(nuevaVenta);
        
        // Ahora procesamos cada detalle
        for (DetalleVenta detalle : cabeceraVenta.getDetalles()) {
            // Validaciones
            if (detalle.getCantidad() == null || detalle.getSubtotal() == null) {
                throw new IllegalArgumentException("Cantidad y subtotal son requeridos en los detalles");
            }
            
            if ((detalle.getProducto() == null && detalle.getPlato() == null) ||
                (detalle.getProducto() != null && detalle.getPlato() != null)) {
                throw new IllegalArgumentException("Cada detalle debe tener exactamente un producto o un plato");
            }
            
            // Crear nuevo detalle y establecer sus valores
            DetalleVenta nuevoDetalle = new DetalleVenta();
            nuevoDetalle.setCabeceraVenta(nuevaVenta);
            nuevoDetalle.setCantidad(detalle.getCantidad());
            nuevoDetalle.setSubtotal(detalle.getSubtotal());
            nuevoDetalle.setPlato(detalle.getPlato());
            nuevoDetalle.setProducto(detalle.getProducto());
            
            // Actualizar stock si es un producto
            if (detalle.getProducto() != null) {
                Producto producto = productoRepository.findById(detalle.getProducto().getId()).get();
                producto.setStock(producto.getStock() - detalle.getCantidad());
                productoRepository.save(producto);
            }
            
            // Guardar el detalle
            detalleVentaRepository.save(nuevoDetalle);
            nuevaVenta.getDetalles().add(nuevoDetalle);
        }
        return nuevaVenta;
    }

    public CabeceraVenta buscarPorId(Long id) {
        return cabeceraVentaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + id));
    }

    @Transactional
    public void eliminarVenta(Long id) {
        if (!cabeceraVentaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró la venta con ID: " + id);
        }
        cabeceraVentaRepository.deleteById(id);
    }
}