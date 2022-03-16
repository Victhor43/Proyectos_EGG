package jpa_libreria.persistenca;

import java.util.List;
import jpa_libreria.entidades.Cliente;

public class ClienteDAO extends DAO {

    public void guardar(Cliente objeto) {
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

    public void editar(Cliente objeto) {
        Cliente c = null;
        try {
            conectar();
            em.getTransaction().begin();
            c = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

    }

    public void eliminar(Cliente objeto) {
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

    public List<Cliente> mostrarTodos() {
        List<Cliente> clientes = null;
        try {
            conectar();
            clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();

            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return clientes;

    }

    public Cliente buscarPorDNI(Long DNI) {
        Cliente c = null;
        try {
            conectar();
            c = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento LIKE :documente")
                    .setParameter("documento", DNI)
                    .getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return c;
    }
}
