package net.suyudi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suyudi.blog.entityes.model.Categories;

/**
 * CategoriesRepository
 */
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    
}