package org.itson.dogosobregon_servivios;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.itson.dogosobregon_dominio.Pedido;
import org.itson.dogosobregon_persistencia.IPedidoDAO;
import org.itson.dogosobregon_persistencia.PedidoDAO;
import org.itson.dogosobregon_utilities.JPAUtil;

public class PedidoService implements IPedidoService {

    private IPedidoDAO pedidoDao;

    public PedidoService() {
        this.pedidoDao = new PedidoDAO();
    }

    private void validarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (pedido.getCliente() == null) {
            throw new IllegalArgumentException("El pedido debe tener un cliente asociado.");
        }
        if (pedido.getMetodoPago() == null) {
            throw new IllegalArgumentException("El método de pago es obligatorio.");
        }
    }

    @Override
    public void guardar(Pedido entidad) {
        validarPedido(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDao.guardar(entidad, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Pedido entidad) {
        validarPedido(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDao.actualizar(entidad, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return pedidoDao.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDao.eliminar(id, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return pedidoDao.buscarTodos(em);
        } finally {
            em.close();
        }
    }
}
