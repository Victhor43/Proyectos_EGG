package jpa_libreria.persistenca;

import java.util.List;
import jpa_libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO{

    
    public void guardar(Prestamo objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
        }
    }

    public void editar(Prestamo objeto) {
        Prestamo p = null;
        try {
            conectar();
            em.getTransaction().begin();
            p = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

    }

    public void eliminar(Prestamo objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public List<Prestamo> mostrarTodos() {
        List<Prestamo> prestamos = null;
        try {
            conectar();
            prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();

            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return prestamos;

    }
    
    public Prestamo buscarPorId(Integer id){
         return em.find(Prestamo.class, id);
    }

}
