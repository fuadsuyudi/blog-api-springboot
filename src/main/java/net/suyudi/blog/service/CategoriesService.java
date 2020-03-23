package net.suyudi.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.suyudi.blog.entityes.model.Categories;
import net.suyudi.blog.repository.CategoriesRepository;

/**
 * CategoriesService
 */
@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Categories update(Integer id, Categories categories) {
        categories.setId(id);

        return categoriesRepository.save(categories);
    }
}