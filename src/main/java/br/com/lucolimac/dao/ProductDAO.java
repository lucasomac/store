package br.com.lucolimac.dao;

import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDAO {

    private final EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Product product) {
        em.persist(product);
    }

    public void update(Product product) {
        em.merge(product);
    }

    public void remove(Product product) {
        product = em.merge(product);
        em.remove(product);
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :name";
        return em.createQuery(jpql, Product.class).setParameter("name", name).getResultList();
    }

    public List<Product> findByCategoryName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name LIKE :name";
        return em.createQuery(jpql, Product.class).setParameter("name", name).getResultList();
    }

    public BigDecimal findPriceByProductName(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, BigDecimal.class).setParameter("name", name).getSingleResult();
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }
}
