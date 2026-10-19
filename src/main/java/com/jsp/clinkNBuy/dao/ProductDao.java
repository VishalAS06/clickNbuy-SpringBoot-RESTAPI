
package com.jsp.clinkNBuy.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.clinkNBuy.entity.Product;
import com.jsp.clinkNBuy.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAllProducts(Pageable pageable) {
        return entityManager.createQuery(
                "SELECT p FROM Product p", Product.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public Product findProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void saveProduct(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    public boolean isProductUnique(
            String name, String brand, Double price) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(p) FROM Product p "
                + "WHERE p.name = :name "
                + "AND p.brand = :brand "
                + "AND p.price = :price", Long.class)
                .setParameter("name", name)
                .setParameter("brand", brand)
                .setParameter("price", price)
                .getSingleResult();

        return count == 0;
    }

    public List<Product> fetchProducts(
            User user, Pageable pageable) {
        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.user = :user",
                Product.class)
                .setParameter("user", user)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    public List<Product> saveAllProducts(List<Product> products) {
        for (Product product : products) {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        }
        return products;
    }

    public List<Product> findApprovedProducts(Pageable pageable) {
        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.approved = true",
                Product.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public Product findProductByIdAndApprovedTrue(Long id) {
        List<Product> products = entityManager.createQuery(
                "SELECT p FROM Product p "
                + "WHERE p.id = :id AND p.approved = true",
                Product.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();

        return products.isEmpty() ? null : products.get(0);
    }
}
