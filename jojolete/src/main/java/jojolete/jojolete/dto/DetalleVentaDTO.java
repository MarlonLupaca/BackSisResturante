package jojolete.jojolete.dto;

public class DetalleVentaDTO {

    private Long cabeceraId;
    private String cajera;
    private String fecha;
    private String hora;
    private Double total;
    private Long detalleId;
    private Integer cantidad;
    private Double subtotal;
    private String codigoProducto;
    private String nombreProducto;
    private Double precioVenta;
    private Integer stockProducto;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(Long cabeceraId, String cajera, String fecha, String hora, Double total, Long detalleId, Integer cantidad, Double subtotal, String codigoProducto, String nombreProducto, Double precioVenta, Integer stockProducto) {
        this.cabeceraId = cabeceraId;
        this.cajera = cajera;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.detalleId = detalleId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.stockProducto = stockProducto;
    }
    

    public Long getCabeceraId() {
        return cabeceraId;
    }

    public void setCabeceraId(Long cabeceraId) {
        this.cabeceraId = cabeceraId;
    }

    public String getCajera() {
        return cajera;
    }

    public void setCajera(String cajera) {
        this.cajera = cajera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Long detalleId) {
        this.detalleId = detalleId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }
}
