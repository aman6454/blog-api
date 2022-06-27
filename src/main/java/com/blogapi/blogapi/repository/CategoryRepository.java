package com.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
