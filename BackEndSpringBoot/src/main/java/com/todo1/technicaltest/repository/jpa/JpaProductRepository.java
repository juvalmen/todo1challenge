package com.todo1.technicaltest.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo1.technicaltest.model.Product;

public interface JpaProductRepository extends JpaRepository<Product, Long> {

}
