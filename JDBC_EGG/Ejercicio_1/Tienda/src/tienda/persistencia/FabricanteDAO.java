
package tienda.persistencia;

import tienda.entidades.Fabricante;


public class FabricanteDAO extends DAO{
    
   public void añadirFabricante (Fabricante fabricante) throws Exception {

        try {
            String sql = "INSERT INTO fabricante VALUES("
                    + fabricante.getCodigo()
                    + ", '"
                    + fabricante.getNombre()
                    + "' "
                    + ")";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }
    
     public Fabricante buscarNombre(String nombre) throws Exception {
        Fabricante fabricante = null;

        try {

            String sql = "SELECT * FROM fabricante WHERE nombre LIKE '" + nombre + "'";

            consultarBase(sql);

            while (resultado.next()) {

                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return fabricante;
    }
    
    
}
