package br.com.lucolimac.tests;

import br.com.lucolimac.dao.CategoryDao;
import br.com.lucolimac.dao.ProductDao;
import br.com.lucolimac.model.Category;
import br.com.lucolimac.model.Product;
import br.com.lucolimac.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Criteria {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao produtoDao = new ProductDao(em);
        List<Product> produtos = produtoDao.findByParametersWithCriteria(null, null, LocalDate.now());
        produtos.forEach(p -> System.out.println(p.getName()));
    }

    private static void popularBancoDeDados() {
        Category celulares = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category informatica = new Category("INFORMATICA");

        Product celular = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Product macbook = new Product("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao produtoDao = new ProductDao(em);
        CategoryDao categoriaDao = new CategoryDao(em);

        em.getTransaction().begin();

        categoriaDao.create(celulares);
        categoriaDao.create(videogames);
        categoriaDao.create(informatica);

        produtoDao.create(celular);
        produtoDao.create(videogame);
        produtoDao.create(macbook);

        em.getTransaction().commit();
        em.close();
    }

}