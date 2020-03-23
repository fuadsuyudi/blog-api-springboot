package net.suyudi.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.suyudi.blog.entityes.model.Comment;
import net.suyudi.blog.repository.CommentRepository;

/**
 * CommentService
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment update(Integer id, Comment comment) {
        comment.setId(id);

        return commentRepository.save(comment);
    }
}