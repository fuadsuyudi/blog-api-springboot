package net.suyudi.blog.service;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponsePage;
import net.suyudi.blog.entityes.model.Blog;
import net.suyudi.blog.entityes.request.BlogDto;

public interface BlogService {
    public Blog create(Blog blog);
    public Blog update(Integer id, BlogDto blogDto) throws NotFoundException;
    public Boolean delete(Integer id);

    public ResponsePage findAll(String title, Integer page, Integer perpage);
}