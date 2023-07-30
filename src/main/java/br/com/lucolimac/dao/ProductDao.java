package br.com.lucolimac.dao;

import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductDao {

    private final EntityManager em;

    public ProductDao(EntityManager em) {
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
        String jpql = "SELECT p FROM Product p WHERE p.category.id.name LIKE :name";
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


    public List<Product> findByParameters(String name, BigDecimal price, LocalDate createdDate) {
        String jpql = "SELECT p FROM Product p WHERE 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " AND p.name = :name ";
        }
        if (price != null) {
            jpql += " AND p.preco = :preco ";
        }
        if (createdDate != null) {
            jpql += " AND p.dataCadastro = :dataCadastro ";
        }
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (price != null) {
            query.setParameter("price", price);
        }
        if (createdDate != null) {
            query.setParameter("createdDate", createdDate);
        }

        return query.getResultList();
    }

    public List<Product> findByParametersWithCriteria(String name, BigDecimal price, LocalDate createdDate) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filters = builder.and();
        if (name != null && !name.trim().isEmpty()) {
            filters = builder.and(filters, builder.equal(from.get("name"), name));
        }
        if (price != null) {
            filters = builder.and(filters, builder.equal(from.get("price"), price));
        }
        if (createdDate != null) {
            filters = builder.and(filters, builder.equal(from.get("createdDate"), createdDate));
        }
        query.where(filters);

        return em.createQuery(query).getResultList();
    }
}
