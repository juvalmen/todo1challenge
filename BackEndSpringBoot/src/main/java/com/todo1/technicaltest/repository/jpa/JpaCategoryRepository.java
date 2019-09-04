package com.todo1.technicaltest.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo1.technicaltest.model.Category;

public interface JpaCategoryRepository extends JpaRepository<Category, Long>{

}
