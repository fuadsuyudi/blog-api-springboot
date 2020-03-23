package net.suyudi.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.suyudi.blog.entityes.model.Blog;
import net.suyudi.blog.repository.BlogRepository;

/**
 * BlogService
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog update(Integer id, Blog blog) {
        blog.setId(id);

        return blogRepository.save(blog);
    }
}