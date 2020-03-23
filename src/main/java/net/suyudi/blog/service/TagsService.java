package net.suyudi.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.suyudi.blog.entityes.model.Tags;
import net.suyudi.blog.repository.TagsRepository;

/**
 * TagsService
 */
@Service
public class TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    public Tags update(Integer id, Tags tags) {
        tags.setId(id);

        return tagsRepository.save(tags);
    }
}