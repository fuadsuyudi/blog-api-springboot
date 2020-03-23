package net.suyudi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.suyudi.blog.entityes.model.Comment;

/**
 * CommentRepository
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * from Comment WHERE blog_id = ?1", nativeQuery = true)
    Comment findCommentBlog(Integer blog_id);
    // Comment findByBlog_id(Integer blog_id);

    @Query(value = "SELECT comment from Comment comment WHERE comment.id = ?1 and comment.blog_id = ?2", nativeQuery = true)
    Comment findCommentId(Integer id, Integer blog_id);
}