package br.com.lucolimac.tests;

import br.com.lucolimac.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class ProductRegistration {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("store");
        EntityManager em = factory.createEntityManager();
        Product cellPhone = new Product();
        cellPhone.setName("Xiaomi Redmi Note 10");
        cellPhone.setDescription("A great cell phone");
        cellPhone.setPrice(BigDecimal.valueOf(1245.0));

        em.getTransaction().begin();
        em.persist(cellPhone);
        em.getTransaction().commit();
        em.close();
    }
}
