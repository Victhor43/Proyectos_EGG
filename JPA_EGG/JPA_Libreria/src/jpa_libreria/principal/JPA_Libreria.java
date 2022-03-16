package jpa_libreria.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jpa_libreria.entidades.Libro;
import jpa_libreria.servicios.AutorService;
import jpa_libreria.servicios.EditorialService;
import jpa_libreria.servicios.LibroService;

public class JPA_Libreria {

    public static void main(String[] args) throws Exception {

        AutorService sAutor = new AutorService();
        EditorialService sEditorial = new EditorialService();
        LibroService sLibro = new LibroService();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        System.out.println("con que quiere trabajar?");
        System.out.println("1) Libros \n"
                + "2) Autores \n"
                + "3) Editoriales \n");

        int opc1 = leer.nextInt();

        int opc2 = 0; // opciones del switch 2

        Long isbn = 0L;
        String titulo = null;
        Integer anio = 0;
        Integer ejemplares = 0;
        Integer ejemPrestados = 0;
        int idAutor = 0;
        int idEditorial = 0;
        boolean alta = true;

//        sAutor.crear("Borges");
//        sAutor.crear("Xsd");
//        
//        sEditorial.crear("la primera");
//        sEditorial.crear("Milenio");
        //
//        sEditorial.crear("Probando");

        sAutor.crear("asdfg");

        switch (opc1) {

            case 1:
                System.out.println("Que tarea desea realizar");
                System.out.println("1- Ingresar un libro nuevo\n"
                        + "2- Editar un libro\n"
                        + "3- Eliminar un libro\n"
                        + "4- Buscar un libro");
                opc2 = leer.nextInt();

                switch (opc2) {  //switch 2
                    case 1:
                        System.out.println("Ingrese el ISBN");
                        isbn = leer.nextLong();
                        System.out.println("Ingrese el titulo");
                        titulo = leer.next();
                        System.out.println("Ingrese el año");
                        anio = leer.nextInt();
                        System.out.println("Ingrese la cantidad de ejemplares");
                        ejemplares = leer.nextInt();
                        System.out.println("Ingrese el id del autor");
                        idAutor = leer.nextInt();
                        System.out.println("Ingrese el id de la editorial");
                        idEditorial = leer.nextInt();

                        sLibro.crear(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
                        break;

                    case 2:
                        System.out.println("Ingrese el ISBN del libro a modificar");
                        isbn = leer.nextLong();
                        sLibro.inexistente(isbn);

                        System.out.println("Que desea editar");
                        System.out.println("1- titulo \n"
                                + "2- año\n"
                                + "3- ejemplares\n"
                                + "4- ejemplares prestados\n"
                                + "5- autor\n"
                                + "6- editorial");
                        int opc3 = leer.nextInt();
                        switch (opc3) {  // switch 3
                            case 1:
                                System.out.println("Ingrese el nuevo titulo");
                                titulo = leer.next();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            case 2:
                                System.out.println("Ingrese el nuevo año");
                                anio = leer.nextInt();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            case 3:
                                System.out.println("Ingrese el nuevo numero de ejemplares");
                                ejemplares = leer.nextInt();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            case 4:
                                System.out.println("Ingrese la cantidad de ejemplares prestados");
                                ejemPrestados = leer.nextInt();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            case 5:
                                System.out.println("Ingrese el id del nuevo autor");
                                idAutor = leer.nextInt();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            case 6:
                                System.out.println("Ingrese el nuevo id de la editorial");
                                idEditorial = leer.nextInt();
                                sLibro.modificar(isbn, titulo, anio, ejemplares, ejemPrestados, alta, idAutor, idEditorial);
                                break;

                            default: //SW3
                                System.out.println("Opcion invalida");
                        }

                        break;

                    case 3:
                        System.out.println("Ingrese el ISBN del libro para borrar");
                        isbn = leer.nextLong();

                        sLibro.eliminar(isbn);

                        break;

                    case 4:
                        System.out.println("Por que parametro quiere buscar libros");
                        System.out.println("1- Todos \n"
                                + "2- ISBN\n"
                                + "3- Titulo\n"
                                + "4- Autor");
                        int opc4 = leer.nextInt();

                        List<Libro> libros = new ArrayList();
                        switch (opc4) { //SW4
                            case 1:
                                libros = sLibro.mostrarTodos();

                                for (Libro aux : libros) {
                                    System.out.println(aux.toString());

                                }

                                break;

                            case 2:
                                System.out.println("Ingrese el ISBN");
                                isbn = leer.nextLong();

                                System.out.println(sLibro.buscarPorIsbn(isbn).toString());
                                break;

                            case 3:
                                System.out.println("Ingrese el Titulo");
                                titulo = leer.next();

                                libros = sLibro.buscarPorTitulo(titulo);

                                for (Libro aux : libros) {
                                    System.out.println(aux.toString());

                                }
                                break;

                            case 4:
                                System.out.println("Ingrese el id de autor");
                                idAutor = leer.nextInt();

                                libros = sLibro.buscarPorAutor(idAutor);

                                for (Libro aux : libros) {
                                    System.out.println(aux.toString());

                                }
                                break;
                        }
                        break;

                    default:// SW2
                        System.out.println("Opcion no valida");

                }

                break;

            default:    //SW1
                System.out.println("Opcion no valida");
        }
    }

}
