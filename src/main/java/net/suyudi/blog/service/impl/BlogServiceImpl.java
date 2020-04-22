package net.suyudi.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponsePage;
import net.suyudi.blog.entityes.model.Blog;
import net.suyudi.blog.entityes.request.BlogDto;
import net.suyudi.blog.repository.BlogRepository;
import net.suyudi.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog update(Integer id, BlogDto blogDto) throws NotFoundException {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog id " + id + " NotFound"));

        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());

        return blogRepository.save(blog);
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            blogRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponsePage findAll(Integer page, Integer perpage) {
        page = page - 1;

        Pageable paging = PageRequest.of(page, perpage, Sort.by("id").descending());
        Page<Blog> blog = blogRepository.findAll(paging);
        
        return new ResponsePage(blog);
    }

}