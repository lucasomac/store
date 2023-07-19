package br.com.lucolimac.dao;

import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;

public class ProductDAO {

    private EntityManager em;

    public ProductDAO(EntityManager em) {
    }

    public void create(Product product) {
        this.em.persist(product);
    }
}
