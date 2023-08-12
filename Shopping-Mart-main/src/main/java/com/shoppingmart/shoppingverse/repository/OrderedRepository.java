package com.shoppingmart.shoppingverse.repository;

import com.shoppingmart.shoppingverse.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
