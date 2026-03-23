package org.itson.dogosobregon_servivios;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.itson.dogosobregon_dominio.PedidoDetalle;
import org.itson.dogosobregon_persistencia.IPedidoDetalleDAO;
import org.itson.dogosobregon_persistencia.PedidoDetalleDAO;
import org.itson.dogosobregon_utilities.JPAUtil;

public class PedidoDetalleService implements IPedidoDetalleService {

    private IPedidoDetalleDAO pedidoDetalleDao;

    public PedidoDetalleService() {
        this.pedidoDetalleDao = new PedidoDetalleDAO();
    }

    private void validarPedidoDetalle(PedidoDetalle detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle del pedido no puede ser nulo.");
        }
        if (detalle.getPedido() == null) {
            throw new IllegalArgumentException("El detalle debe estar asociado a un pedido.");
        }
        if (detalle.getHotdog() == null) {
            throw new IllegalArgumentException("El detalle debe especificar un hotdog.");
        }
        if (detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
    }

    @Override
    public void guardar(PedidoDetalle entidad) {
        validarPedidoDetalle(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDetalleDao.guardar(entidad, em);
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
    public void actualizar(PedidoDetalle entidad) {
        validarPedidoDetalle(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDetalleDao.actualizar(entidad, em);
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
    public PedidoDetalle buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return pedidoDetalleDao.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            pedidoDetalleDao.eliminar(id, em);
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
    public List<PedidoDetalle> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return pedidoDetalleDao.buscarTodos(em);
        } finally {
            em.close();
        }
    }
}
