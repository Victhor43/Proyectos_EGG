package jpa_libreria.servicios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import jpa_libreria.entidades.Cliente;
import jpa_libreria.entidades.Libro;
import jpa_libreria.entidades.Prestamo;
import jpa_libreria.persistenca.PrestamoDAO;

public class PrestamoService {

    private final PrestamoDAO dao;
    private LibroService sLibro;
    private ClienteServicio sCliente;
    private LocalDate fecha;

    public PrestamoService() {
        this.dao = new PrestamoDAO();
        sLibro = new LibroService();
        sCliente = new ClienteServicio();
        this.fecha = LocalDate.now();
    }

    public void prestamoCrear(Date fechaPrestamo, Long isbn, Long dni) throws Exception {

        validarPrestamo(fechaPrestamo, isbn, dni);

        Prestamo p = new Prestamo();

        Libro libro = sLibro.buscarPorIsbn(isbn);
        Cliente cliente = sCliente.buscarPorDNI(dni);

        p.setFechaPrestamo(fechaPrestamo);
        p.setFechaDevolucion(null);

        p.setLibro(libro);
        p.setCliente(cliente);

       
        sLibro.modificar(isbn, null, null, null, 1 , true, 0, 0); // modifico la cantidad de libros restantes y prestados
        
        dao.guardar(p);
    }

    public void devolucionModificar(Integer id, Date fechaDevolucion) throws Exception {

        validarDevolucion(id, fechaDevolucion);

        Prestamo p = buscarPorId(id);

        p.setFechaDevolucion(fechaDevolucion);
        
        Libro libro = p.getLibro();
        
       sLibro.modificar(libro.getIsbn(), null, null, null, -1 , true, 0, 0); // modifico la cantidad de libros restantes y prestados

        dao.editar(p);
        
        eliminar(id);

    }

    public void eliminar(Integer id) {

        Prestamo p = buscarPorId(id);
        dao.eliminar(p);
    }

    public List<Prestamo> mostrarTodos() {
        return dao.mostrarTodos();
    }

    public void validarPrestamo(Date fechaPrestamo, Long isbn, Long dni) throws Exception {

        Libro libro = sLibro.buscarPorIsbn(isbn);
        Cliente cliente = sCliente.buscarPorDNI(dni);

        Date fechaAux = Date.from(fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        if (fechaPrestamo == null) {
            throw new Exception("La fecha no puede ser nula");
        }
        if (fechaPrestamo.after(fechaAux)) {
            throw new Exception("La fecha de prestamo no puede ser anterior a la actual");
        }
        if (libro.getEjemplaresRestantes() < 1) {
            throw new Exception("No hay mas ejemplares para prestar");
        }

    }

    public void validarDevolucion(Integer id, Date fechaDevolucion) throws Exception {

        Date fechaAux = Date.from(fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        Prestamo p = buscarPorId(id);
        if (p == null) {
            throw new Exception("El prestamo no existe");
        }

        if (fechaDevolucion.after(fechaAux)) {
            throw new Exception("La fecha de devolucion no puede ser anterior a la actual");
        }

        Libro libro = p.getLibro();

        if (libro.getEjemplaresRestantes() == libro.getEjemplares()) {
            throw new Exception("Todos los ejemplares ya fueron devueltos");
        }

    }

    public Prestamo buscarPorId(Integer id) {
        return dao.buscarPorId(id);

    }

}
