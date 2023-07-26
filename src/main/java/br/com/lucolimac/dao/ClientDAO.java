package br.com.lucolimac.dao;

import br.com.lucolimac.model.Category;
import br.com.lucolimac.model.Client;
import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;

public class ClientDAO {

    private final EntityManager em;

    public ClientDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Client client) {
        em.persist(client);
    }

    public void update(Client client) {
        em.merge(client);
    }

    public void delete(Client client) {
        client = em.merge(client);
        em.remove(client);
    }

    public Client findById(Long id) {
        return em.find(Client.class, id);
    }
}
