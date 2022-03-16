package jpa_libreria.persistenca;

import java.util.List;
import jpa_libreria.entidades.Editorial;

public final class EditorialDAO extends DAO {

    public void guardar(Editorial objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public List<Editorial> mostrarTodos() {
        List<Editorial> editoriales = null;
        try {
            conectar();
            editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
            desconectar();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
        return editoriales;
    }

    public Editorial editar(Editorial objeto) {
        Editorial e = null;
        try {
            conectar();
            em.getTransaction().begin();
            e = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception ex) {
            throw ex;
        } finally {
            desconectar();
        }

        return e;
    }

    public void eliminar(Editorial objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    public Editorial buscarPorId(Integer id) {
        return em.find(Editorial.class, id);
    }

    public List<Editorial> buscarPorNombre(String nombre) {
        List<Editorial> editoriales = null;
        try {
            conectar();

            editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre ")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();

            desconectar();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return editoriales;
    }
}
