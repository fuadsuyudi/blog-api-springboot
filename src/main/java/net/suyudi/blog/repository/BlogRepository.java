package net.suyudi.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.suyudi.blog.entityes.model.Blog;

/**
 * BlogRepository
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {

	Page<Blog> findByTitleContaining(String title, Pageable paging);

    
}