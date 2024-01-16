package com.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.beans.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean findByCategory(String category);
}
