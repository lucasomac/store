package br.com.lucolimac.tests;

import br.com.lucolimac.dao.CategoryDAO;
import br.com.lucolimac.dao.ClientDAO;
import br.com.lucolimac.dao.OrderDAO;
import br.com.lucolimac.dao.ProductDAO;
import br.com.lucolimac.model.*;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderRegistration {
    public static void main(String[] args) {
        registerProduct();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        ClientDAO clientDAO = new ClientDAO(em);
        OrderDAO orderDAO = new OrderDAO(em);

        Product product = productDAO.findById(1L);
        Client client = clientDAO.findById(1L);

        em.getTransaction().begin();

        Order order = new Order(client);
        order.addOrderItem(new OrderItem(5, product, order));
        orderDAO.create(order);

        em.getTransaction().commit();

    }

    public static void registerProduct() {
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);
        ClientDAO clientDAO = new ClientDAO(em);

        Category cellPhoneCategory = new Category("CELLPHONE");
        Product cellPhone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellPhoneCategory);
        Client client = new Client("Lucas", "0394059");

        em.getTransaction().begin();
        categoryDAO.create(cellPhoneCategory);
        productDAO.create(cellPhone);
        clientDAO.create(client);
        em.getTransaction().commit();

        em.close();
    }
}
