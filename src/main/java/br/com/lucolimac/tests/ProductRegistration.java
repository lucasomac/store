package br.com.lucolimac.tests;

import br.com.lucolimac.dao.CategoryDao;
import br.com.lucolimac.dao.ProductDao;
import br.com.lucolimac.model.Category;
import br.com.lucolimac.model.Product;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductRegistration {
    public static void main(String[] args) {
        registerProduct();
        Long id = 1L;
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDAO = new ProductDao(em);

        Product product = productDAO.findById(id);
        System.out.printf("The price of product %d is: %s%n", id, product.getPrice());
        List<Product> todos = productDAO.findAll();
        todos.forEach(p -> System.out.println(p.getName()));
        List<Product> allCellPhone = productDAO.findByCategoryName("CELLPHONE");
        allCellPhone.forEach(cellphone -> System.out.println(cellphone.getName()));

        BigDecimal productPrice = productDAO.findPriceByProductName("Xiaomi Redmi");
        System.out.printf("The price of product is: %s%n", productPrice);
    }

    private static void registerProduct() {
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDAO = new ProductDao(em);
        CategoryDao categoryDAO = new CategoryDao(em);
        Category cellPhoneCategory = new Category("CELLPHONE");
        Product cellPhone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellPhoneCategory);
        em.getTransaction().begin();
        categoryDAO.create(cellPhoneCategory);
        productDAO.create(cellPhone);
        em.getTransaction().commit();
        em.close();
    }
}
