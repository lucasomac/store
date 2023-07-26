package br.com.lucolimac.dao;

import br.com.lucolimac.model.Order;
import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDAO {

    private final EntityManager em;

    public OrderDAO(EntityManager em) {
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
}
