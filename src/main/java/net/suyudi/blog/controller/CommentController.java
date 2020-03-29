package net.suyudi.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponseBase;
import net.suyudi.blog.entityes.model.Comment;
import net.suyudi.blog.repository.CommentRepository;

/**
 * CommentController
 */
@RestController
@RequestMapping("/blogs")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{blog}/comments")
    public ResponseEntity<ResponseBase> getComment(@PathVariable Integer blog) {
        ResponseBase response = new ResponseBase<>();

        response.setData(commentRepository.findCommentBlog(blog));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{blog}/comments/{id}")
    public ResponseEntity<ResponseBase> getCommentById(@PathVariable Integer blog, @PathVariable Integer id) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Comment comment = commentRepository.findCommentId(id, blog);

        response.setData(comment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{blog}/comments")
    public ResponseEntity<ResponseBase> postComment(@PathVariable Integer blog, @RequestBody Comment comment) {
        ResponseBase response = new ResponseBase<>();

        try {
            response.setData(commentRepository.save(comment));
            
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{blog}/comments/{id}")
    public ResponseEntity<ResponseBase> deleteComment(@PathVariable Integer blog, @PathVariable Integer id) {
        ResponseBase response = new ResponseBase<>();

        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}