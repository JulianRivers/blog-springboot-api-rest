package com.sistema.blog.sistemablogspringbootapirest.model.dto;

import com.sistema.blog.sistemablogspringbootapirest.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String content;

    private Set<Comment> comments;
}
