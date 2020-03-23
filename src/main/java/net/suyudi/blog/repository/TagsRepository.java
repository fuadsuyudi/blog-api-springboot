package net.suyudi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suyudi.blog.entityes.model.Tags;

/**
 * TagsRepository
 */
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    
}