package net.suyudi.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.suyudi.blog.entityes.model.Author;
import net.suyudi.blog.repository.AuthorRepository;

/**
 * AuthorService
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Author save(Author author) {
        author.setPassword(passwordEncoder().encode(author.getPassword()));
        
        return authorRepository.save(author);
    }

    public Author changePassword(Integer id, Author author) {
        author.setId(id);
        author.setPassword(passwordEncoder().encode(author.getPassword()));

        return authorRepository.save(author);
    }

    public Author update(Integer id, Author author) {
        author.setId(id);

        return authorRepository.save(author);
    }
}