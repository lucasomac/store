package br.com.lucolimac.dao;

import br.com.lucolimac.model.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {

    private final EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Category category) {
        em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }
    public void delete(Category category) {
        category = em.merge(category);
        em.remove(category);
    }
}
