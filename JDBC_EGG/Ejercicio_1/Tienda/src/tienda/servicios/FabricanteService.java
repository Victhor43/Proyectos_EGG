package tienda.servicios;

import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

public class FabricanteService {

    private FabricanteDAO dao;

    public FabricanteService() {            ///public Service() {
        this.dao = new FabricanteDAO();     ///this.dao = new DAO();
    }                                       ///}

public void añadirFabricante(int codigo, String nombre) throws Exception {

        validar(codigo, nombre);

        Fabricante fabricante = new Fabricante();

        fabricante.setCodigo(codigo);
        fabricante.setNombre(nombre);
        
        dao.añadirFabricante(fabricante);
    }

    private void validar(int codigo, String nombre) throws Exception {

        if (codigo < 0) { // corroborar que se ingreso cada campo del elemento
            throw new Exception("Debe indicar el codigo");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe indicar el nombre");
        }
        
        Fabricante f = buscarNombre(nombre);
       
        if (f != null) {
            throw new Exception("Ya existe el fabricante");
        }
    }

    private Fabricante buscarNombre(String nombre) throws Exception {
        
        Fabricante fabricante = dao.buscarNombre(nombre);
        return fabricante;
        
    }



}
