package net.suyudi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suyudi.blog.entityes.model.Author;

/**
 * AuthorRepository
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>{

	Author findByUsername(String username);

    
}