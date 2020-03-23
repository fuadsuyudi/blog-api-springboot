package net.suyudi.blog.entityes.request;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

/**
 * AuthorPasswordDto
 */
@Getter
@Setter
public class AuthorPasswordDto {

    @Column(length = 150, nullable = false)
    private String password;
}