package net.suyudi.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.apache.tomcat.util.http.fileupload.FileUpload;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponseBase;
import net.suyudi.blog.entityes.model.Blog;
import net.suyudi.blog.entityes.model.Tags;
import net.suyudi.blog.repository.BlogRepository;
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
    public ResponseEntity<ResponseBase> postBlog(@RequestParam MultipartFile request, @RequestBody Blog blog) {
        ResponseBase response = new ResponseBase<>();

        // blog.getTag().add(new Tags("rest"));
        // blog.getTag().add(new Tags("api"));

        System.out.println("fuad " + request.getOriginalFilename().toString());

        try {
            // response.setData(blogRepository.save(blog));

            File upl = new File("assets/img/" + request.getOriginalFilename());
            upl.createNewFile();
            FileOutputStream fout = new FileOutputStream(upl);
            fout.write(request.getBytes());
            fout.close();
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase> putBlog(@PathVariable Integer id, @RequestBody Blog blog) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Tags id " + id + " NotFound"));

        try {
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