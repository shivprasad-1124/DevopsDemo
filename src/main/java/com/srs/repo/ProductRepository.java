package com.srs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
