import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cliente> clientes = new ArrayList<>();  
        List<Producto> productosDisponibles = new ArrayList<>(); 

      
        productosDisponibles.add(new Producto("globos", "grande", "rojo", 100, 5.00));
        productosDisponibles.add(new Producto("Sombrilla ", "grande", "negra", 200, 50.00));
        productosDisponibles.add(new Producto("taza", "taza de 1.5 ml", "termica", 1500, 25.00));
        productosDisponibles.add(new Producto("boli", "Bolígrafos personalizados", "de colores", 1500, 25.00));
        productosDisponibles.add(new Producto("Blocs de notas", "Con diseños personalizados y hojas de diferentes colores", "hojas", 1500, 25.00));
        productosDisponibles.add(new Producto("Llaveros", "Llaveros personalidos ", "colgante", 1500, 25.00));
        productosDisponibles.add(new Producto("usb", "taza de 1.5 ml", "termica", 1050, 50.00));
        productosDisponibles.add(new Producto("power bank", "personalizado", "negro", 1500, 250.00));
        productosDisponibles.add(new Producto("gorras", "personalizados", "de colores ", 1500, 200.00));
        productosDisponibles.add(new Producto("camisas", "personalisadas", "folforesentes", 1050, 250.00));

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Realizar Pedido");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Listar Clientes y Pedidos");
            System.out.println("5. Listar Productos Disponibles");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:


                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Ingrese la dirección del cliente: ");
                    String direccionCliente = scanner.nextLine();
                    clientes.add(new Cliente(nombreCliente, direccionCliente));
                    System.out.println("Cliente agregado con éxito.");
                    break;

                case 2:
                  
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados. Por favor, agregue un cliente primero.");
                        break;
                    }
                    System.out.println("Seleccione el cliente (Ingrese el número correspondiente):");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
                    }
                    int clienteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
                        System.out.println("Cliente no válido.");
                        break;
                    }

                    Cliente cliente = clientes.get(clienteIndex);
                    List<Producto> productosParaPedido = new ArrayList<>();

                    System.out.println("Seleccione los productos para el pedido (Ingrese 0 para finalizar):");
                    for (int i = 0; i < productosDisponibles.size(); i++) {
                        Producto producto = productosDisponibles.get(i);
                        System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio() + " (Stock: " + producto.getCantidad() + ")");
                    }

                    while (true) {
                        int productoIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (productoIndex == -1) break;  

                        if (productoIndex < 0 || productoIndex >= productosDisponibles.size()) {
                            System.out.println("Producto no válido.");
                            continue;
                        }

                        Producto productoSeleccionado = productosDisponibles.get(productoIndex);
                        System.out.print("Ingrese la cantidad para " + productoSeleccionado.getNombre() + ": ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();

                        if (cantidad <= 0 || cantidad > productoSeleccionado.getCantidad()) {
                            System.out.println("Cantidad no válida. Máximo disponible: " + productoSeleccionado.getCantidad());
                            continue;
                        }

                        productosParaPedido.add(new Producto(productoSeleccionado.getNombre(), productoSeleccionado.getDescripcion(),
                                productoSeleccionado.getTipo(), cantidad, productoSeleccionado.getPrecio()));
                    }

                    cliente.realizaPedido(productosParaPedido);
                    break;

                case 3:
                  
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                        break;
                    }

                    System.out.println("Seleccione el cliente (Ingrese el número correspondiente):");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
                    }
                    int clienteIndexCancelar = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (clienteIndexCancelar < 0 || clienteIndexCancelar >= clientes.size()) {
                        System.out.println("Cliente no válido.");
                        break;
                    }

                    Cliente clienteCancelar = clientes.get(clienteIndexCancelar);
                    List<OrdenDeCompra> ordenes = clienteCancelar.getOrdenesDeCompra();

                    if (ordenes.isEmpty()) {
                        System.out.println("El cliente no tiene pedidos.");
                        break;
                    }

                    System.out.println("Seleccione el pedido a cancelar (Ingrese el número correspondiente):");
                    for (int i = 0; i < ordenes.size(); i++) {
                        System.out.println((i + 1) + ". Pedido ID: " + ordenes.get(i).getId() + ", Estado: " + ordenes.get(i).getEstado());
                    }

                    int ordenIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (ordenIndex < 0 || ordenIndex >= ordenes.size()) {
                        System.out.println("Pedido no válido.");
                        break;
                    }

                    clienteCancelar.cancelaPedido(ordenes.get(ordenIndex));
                    break;

                case 4:
                   
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println("Cliente: " + c.getNombre() + ", Dirección: " + c.getDireccion());
                            List<OrdenDeCompra> pedidos = c.getOrdenesDeCompra();
                            if (pedidos.isEmpty()) {
                                System.out.println("  No tiene pedidos.");
                            } else {
                                for (OrdenDeCompra orden : pedidos) {
                                    System.out.println("  Pedido ID: " + orden.getId() + ", Estado: " + orden.getEstado());
                                }
                            }
                        }
                    }
                    break;

                case 5:
                 
                    System.out.println("Productos disponibles:");
                    for (Producto producto : productosDisponibles) {
                        System.out.println(producto);
                    }
                    break;

                case 6:
                   
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}