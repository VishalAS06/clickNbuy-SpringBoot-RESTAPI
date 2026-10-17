package com.jsp.clinkNBuy.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.clinkNBuy.entity.Product;
import com.jsp.clinkNBuy.entity.User;



public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByNameAndBrandAndPrice(String name, String brand, Double price);
	java.util.List<Product> findByUser(User user);
	java.util.List<Product> findByUser(User user, org.springframework.data.domain.Pageable pageable);
	
}
