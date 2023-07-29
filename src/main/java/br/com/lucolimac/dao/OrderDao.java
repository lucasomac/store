package br.com.lucolimac.dao;

import br.com.lucolimac.model.Order;
import br.com.lucolimac.model.SalesReport;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDao {

    private final EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void create(Order order) {
        em.persist(order);
    }

    public void update(Order order) {
        em.merge(order);
    }

    public void remove(Order order) {
        order = em.merge(order);
        em.remove(order);
    }

    public BigDecimal totalAmountSold() {
        String jpql = "SELECT SUM(o.amount) FROM Order  o";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<SalesReport> salesReport() {
        return em.createNamedQuery("Order.salesReport", SalesReport.class).getResultList();
    }

    public Order searchOrderWithClient(Long id) {
        return em.createQuery("SELECT o FROM Order o JOIN FETCH o.client WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
