package net.suyudi.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import net.suyudi.blog.entityes.ResponseBase;
import net.suyudi.blog.entityes.model.Author;
import net.suyudi.blog.entityes.request.AuthorDto;
import net.suyudi.blog.entityes.request.AuthorPasswordDto;
import net.suyudi.blog.repository.AuthorRepository;
import net.suyudi.blog.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * AuthorController
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public ResponseEntity<ResponseBase> getAuthor() {
        ResponseBase response = new ResponseBase<>();

        response.setData(authorRepository.findAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseBase> getAuthorById(@PathVariable Integer id) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author id " + id + " NotFound"));

        response.setData(author);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseBase> postAuthor(@RequestBody Author author) {
        ResponseBase response = new ResponseBase<>();

        try {
            response.setData(authorService.save(author));
            
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase> putAuthor(@PathVariable Integer id, @RequestBody AuthorDto authorDto) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author id " + id + " NotFound"));

        try {
            author.setFirst_name(authorDto.getFirst_name());
            author.setLast_name(authorDto.getLast_name());
            author.setUsername(authorDto.getUsername());

            response.setData(authorService.update(id, author));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ResponseBase> putPaswordAuthor(@PathVariable Integer id, @RequestBody AuthorPasswordDto authorPasswordDto) throws NotFoundException {
        ResponseBase response = new ResponseBase<>();

        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author id " + id + " NotFound"));

        try {
            author.setPassword(authorPasswordDto.getPassword());

            response.setData(authorService.changePassword(id, author));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase> deleteAuthor(@PathVariable Integer id) {
        ResponseBase response = new ResponseBase<>();

        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}