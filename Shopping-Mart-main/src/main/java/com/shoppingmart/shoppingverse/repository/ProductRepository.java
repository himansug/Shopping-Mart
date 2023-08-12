package com.shoppingmart.shoppingverse.repository;

import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import com.shoppingmart.shoppingverse.model.Card;
import com.shoppingmart.shoppingverse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.price >= :price and p.productCategory = :category")
    public List<Product> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category);

}
