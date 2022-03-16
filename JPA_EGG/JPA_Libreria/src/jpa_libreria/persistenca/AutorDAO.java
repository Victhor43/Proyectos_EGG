package jpa_libreria.persistenca;

import java.util.List;
import jpa_libreria.entidades.Autor;

public final class AutorDAO extends DAO {

    public void guardar(Autor objeto) {
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

    public List<Autor> mostrarTodos() {
        List<Autor> autores = null;
        try {
            conectar();
            autores = em.createQuery("SELECT a FROM Autor a").getResultList();
            desconectar();

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return autores;
    }

    public Autor editar(Autor objeto) {
        Autor a = null;
        try {
            conectar();
            em.getTransaction().begin();
            a = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return a;
    }

    public void eliminar(Autor objeto) {
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

    public Autor buscarPorId(Integer id) {
        return em.find(Autor.class, id);
    }

    public Autor buscarPorNombre(String nombre) {
        Autor autor = null;
        try {
            conectar();

            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre ")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getSingleResult();

            desconectar();
        } catch (Exception e) {
            return null;
        } finally {
            desconectar();
        }

        return autor;
    }
}
