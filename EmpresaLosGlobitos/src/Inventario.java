public class Inventario {

    private int cantidadDisponible;  
    private int cantidadEnProduccion; 

    
    public Inventario(int cantidadDisponible, int cantidadEnProduccion) {
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadEnProduccion = cantidadEnProduccion;
    }

    
    public void actualizarInventarios(int productosProducidos, int productosVendidos) {
        if (productosProducidos > 0) {
            
            this.cantidadDisponible += productosProducidos;
           
            this.cantidadEnProduccion -= productosProducidos;
        }

        if (productosVendidos > 0) {
            
            if (productosVendidos <= this.cantidadDisponible) {
                this.cantidadDisponible -= productosVendidos;
            } else {
                System.out.println("No hay suficiente stock para cubrir la venta.");
            }
        }

        System.out.println("Inventario actualizado:");
        System.out.println("Cantidad disponible: " + this.cantidadDisponible);
        System.out.println("Cantidad en producción: " + this.cantidadEnProduccion);
    }

    
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadEnProduccion() {
        return cantidadEnProduccion;
    }

    public void setCantidadEnProduccion(int cantidadEnProduccion) {
        this.cantidadEnProduccion = cantidadEnProduccion;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "cantidadDisponible=" + cantidadDisponible +
                ", cantidadEnProduccion=" + cantidadEnProduccion +
                '}';
    }
}