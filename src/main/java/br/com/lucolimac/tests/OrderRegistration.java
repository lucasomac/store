package br.com.lucolimac.tests;

import br.com.lucolimac.dao.CategoryDao;
import br.com.lucolimac.dao.ClientDao;
import br.com.lucolimac.dao.OrderDao;
import br.com.lucolimac.dao.ProductDao;
import br.com.lucolimac.model.*;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderRegistration {
    public static void main(String[] args) {
        registerProduct();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDAO = new ProductDao(em);
        ClientDao clientDAO = new ClientDao(em);
        OrderDao orderDAO = new OrderDao(em);

        Product product = productDAO.findById(1L);
        Client client = clientDAO.findById(1L);

        em.getTransaction().begin();

        Order order = new Order(client);
        order.addOrderItem(new OrderItem(5, product, order));
        orderDAO.create(order);


        em.getTransaction().commit();
        BigDecimal amount = orderDAO.totalAmountSold();
        System.out.println("Valor total: "+amount);
    }

    public static void registerProduct() {
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDAO = new ProductDao(em);
        CategoryDao categoryDAO = new CategoryDao(em);
        ClientDao clientDAO = new ClientDao(em);

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
