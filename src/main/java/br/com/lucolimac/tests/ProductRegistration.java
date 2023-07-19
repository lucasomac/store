package br.com.lucolimac.tests;

import br.com.lucolimac.dao.ProductDAO;
import br.com.lucolimac.model.Product;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ProductRegistration {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        Product cellPhone = new Product();
        cellPhone.setName("Xiaomi Redmi Note 10");
        cellPhone.setDescription("A great cell phone");
        cellPhone.setPrice(BigDecimal.valueOf(1245.0));

        em.getTransaction().begin();
        productDAO.create(cellPhone);
        em.getTransaction().commit();
        em.close();
    }
}
