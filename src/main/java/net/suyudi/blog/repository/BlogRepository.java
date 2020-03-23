package net.suyudi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suyudi.blog.entityes.model.Blog;

/**
 * BlogRepository
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    
}