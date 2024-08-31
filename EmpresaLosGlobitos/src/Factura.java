import java.util.Date;
import java.util.List;

public class Factura {
    private int id;              
    private Date fecha;          
    private double montoTotal;    
    private List<Producto> productos; 

    
    public Factura(int id, List<Producto> productos) {
        this.id = id;
        this.fecha = new Date(); 
        this.productos = productos;
        this.montoTotal = calcularMontoTotal();
    }

    public Factura() {
		// TODO Auto-generated constructor stub
	}

	
    public void generarFactura() {
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay productos para generar la factura.");
            return;
        }

        this.fecha = new Date(); 
        this.montoTotal = calcularMontoTotal(); 

        
        System.out.println("Factura generada con ID: " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Monto Total: $" + montoTotal);
    }

    
    private double calcularMontoTotal() {
        double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getCantidad() * producto.getPrecio(); 
        }
        return total;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        this.montoTotal = calcularMontoTotal(); 
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", montoTotal=" + montoTotal +
                ", productos=" + productos +
                '}';
    }
}