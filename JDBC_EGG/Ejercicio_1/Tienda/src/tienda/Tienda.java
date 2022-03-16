package tienda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.servicios.FabricanteService;
import tienda.servicios.ProductoService;

public class Tienda {

    public static void main(String[] args) throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        ProductoService sProducto = new ProductoService();
        FabricanteService sFabricante = new FabricanteService();

        System.out.println("Que consulta quiere hacer?");
        System.out.println("a) Lista el nombre de todos los productos que hay en la tabla producto.\n"
                + "b) Lista los nombres y los precios de todos los productos de la tabla producto.\n"
                + "c) Listar aquellos productos que su precio esté entre 120 y 202.\n"
                + "d) Buscar y listar todos los Portátiles de la tabla producto.\n"
                + "e) Listar el nombre y el precio del producto más barato.\n"
                + "f) Ingresar un producto a la base de datos.\n"
                + "g) Ingresar un fabricante a la base de datos\n"
                + "h) Editar un producto con datos a elección.\n"
                + "i) Finalizar");

        String opcion = null;

        List<Producto> productos = new ArrayList();

        do {
            opcion = leer.next();
            productos.clear();
            switch (opcion.toLowerCase()) {
                case "a":

                    productos.addAll(sProducto.listarProdcutos());
                    for (Producto aux : productos) {
                        System.out.println(aux.getNombre());
                    }
                    break;

                case "b":
                    productos.addAll(sProducto.listarProdcutos());
                    for (Producto aux : productos) {
                        System.out.println(aux.getNombre() + ", $" + aux.getPrecio());
                    }

                    break;

                case "c":
                    productos.addAll(sProducto.listarPrecio_120a202());
                    for (Producto aux : productos) {
                        System.out.println(aux.toString());

                    }
                    break;

                case "d":
                    productos.addAll(sProducto.listarPortatiles());
                    for (Producto aux : productos) {
                        System.out.println(aux.toString());

                    }
                    break;

                case "e":
                    productos.addAll(sProducto.listarProductoMasBarato());
                    for (Producto aux : productos) {
                        System.out.println(aux.getNombre() + ", $" + aux.getPrecio());

                    }
                    break;

                case "f":
                    System.out.println("Se agregara un nuevo producto");
                    System.out.println("Ingrese codigo");
                    int codigo = leer.nextInt();

                    System.out.println("\nIngrese nombre");
                    String nombre = leer.next();

                    System.out.println("\nIngrese precio");
                    double precio = leer.nextDouble();

                    System.out.println("\nIngrese Codigo de fabricante");
                    int codFabricante = leer.nextInt();

                    sProducto.añadirProducto(codigo, nombre, precio, codFabricante);
                    break;

                case "g":
                    System.out.println("Se agregara un nuevo fabricante");
                    System.out.println("Ingrese codigo");
                    int codigoF = leer.nextInt();

                    System.out.println("\nIngrese nombre");
                    String nombreF = leer.next();

                    sFabricante.añadirFabricante(codigoF, nombreF);
                    break;

                case "h":
                    System.out.println("Ingrese el codigo del producto para modificar");
                    int cod = leer.nextInt();

                    Producto aux = sProducto.buscarPorCodigo(cod);

                    if (aux == null) {
                        throw new Exception("No existe ese producto");
                    }


                    System.out.println("\n que desea modificar?");
                    System.out.println("1 - nombre \n"
                            + "2 - precio \n"
                            + "3 - codigo de fabricante");
                    int op = leer.nextInt();

                    switch (op) {
                        case 1:
                            System.out.println("Ingrese nombre nuevo");
                            String nombreNuevo = leer.next();

                            sProducto.modificarProducto(nombreNuevo, aux.getPrecio(), cod, aux.getCodigo_fabricante());
                            System.out.println("exito");
                            break;

                        case 2:
                            System.out.println("Ingrese precio nuevo");
                            double precioNuevo = leer.nextDouble();

                            sProducto.modificarProducto(aux.getNombre(), precioNuevo, cod, aux.getCodigo_fabricante());
                            break;

                        case 3:
                            System.out.println("Ingrese codigo de fabricante nuevo");
                            int fabric = leer.nextInt();

                            sProducto.modificarProducto(aux.getNombre(), aux.getPrecio(), cod, fabric);
                            break;

                    }
                    break;

                default:
                    System.out.println("Opcion no valida, vuelva a intentar");

            }
        } while (!"i".equals(opcion.toLowerCase())); //(opcion.toLowerCase() != "i")

    }///////////

}///////////

