package com.shoppingmart.shoppingverse.repository;

import com.shoppingmart.shoppingverse.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {


    Optional<Seller> findByEmailId(String emailId);
}
