package net.suyudi.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponseBase;
import net.suyudi.blog.entityes.model.Author;
import net.suyudi.blog.entityes.model.Blog;
import net.suyudi.blog.entityes.model.Categories;
import net.suyudi.blog.entityes.model.Tags;
import net.suyudi.blog.entityes.request.BlogDto;
import net.suyudi.blog.repository.AuthorRepository;
import net.suyudi.blog.repository.BlogRepository;
import net.suyudi.blog.repository.CategoriesRepository;
import net.suyudi.blog.repository.TagsRepository;
import net.suyudi.blog.service.BlogService;

/**
 * BlogController
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private BlogService blogService;

    @GetMapping()
    public ResponseEntity<ResponseBase> getBlog() {
        ResponseBase response = new ResponseBase<>();

        response.setData(blogRepository.findAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseBase> getBlogById(@PathVariable Integer id) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Tags id " + id + " NotFound"));

        response.setData(blog);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseBase> postBlog(@RequestBody Blog blog) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Author author = authorRepository.findById(blog.getAuthor_id()).orElseThrow(() -> new NotFoundException("Blog id " + blog.getAuthor_id() + " NotFound"));
        Categories categories = categoriesRepository.findById(blog.getCategories_id()).orElseThrow(() -> new NotFoundException("Category id " + blog.getCategories_id() + " NotFound"));
        
        List<Integer> tagtag = blog.getTags_id();
        ArrayList<Tags> tags = new ArrayList<Tags>();

        for (Integer tag : tagtag) {
            Tags val = tagsRepository.findById(tag).orElseThrow(() -> new NotFoundException("Tags id " + tag + " NotFound"));
            tags.add(val);
        }

        blog.setAuthor(author);
        blog.setCategories(categories);
        blog.setTag(tags);

        try {
            response.setData(blogRepository.save(blog));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase> putBlog(@PathVariable Integer id, @RequestBody BlogDto blogDto) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog id " + id + " NotFound"));

        try {
            blog.setTitle(blogDto.getTitle());
            blog.setContent(blogDto.getContent());

            response.setData(blogService.update(id, blog));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase> deleteBlog(@PathVariable Integer id) {
        ResponseBase response = new ResponseBase<>();

        try {
            blogRepository.deleteById(id);
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}