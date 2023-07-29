package br.com.lucolimac.tests;

import br.com.lucolimac.dao.CategoryDao;
import br.com.lucolimac.dao.ClientDao;
import br.com.lucolimac.dao.OrderDao;
import br.com.lucolimac.dao.ProductDao;
import br.com.lucolimac.model.*;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class QueryPerformance {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        OrderDao orderDao = new OrderDao(em);
        Order order = orderDao.searchOrderWithClient(1L);
        em.close();
        System.out.println(order.getClient().getName());
    }

    private static void popularBancoDeDados() {
        Category celulares = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category informatica = new Category("INFORMATICA");

        Product celular = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Product macbook = new Product("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        Client Client = new Client("Rodrigo", "123456");

        Order order = new Order(Client);
        order.addOrderItem(new OrderItem(10, order, celular));
        order.addOrderItem(new OrderItem(40, order, videogame));

        Order order2 = new Order(Client);
        order2.addOrderItem(new OrderItem(2, order, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDAO = new ProductDao(em);
        CategoryDao categoryDAO = new CategoryDao(em);
        ClientDao clientDao = new ClientDao(em);
        OrderDao orderDao = new OrderDao(em);

        em.getTransaction().begin();

        categoryDAO.create(celulares);
        categoryDAO.create(videogames);
        categoryDAO.create(informatica);

        productDAO.create(celular);
        productDAO.create(videogame);
        productDAO.create(macbook);

        clientDao.create(Client);

        orderDao.create(order);
        orderDao.create(order2);

        em.getTransaction().commit();
        em.close();
    }
}