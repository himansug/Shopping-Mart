package com.shoppingmart.shoppingverse.repository;

import com.shoppingmart.shoppingverse.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByCardNo(String cardNo);
}
