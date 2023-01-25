package com.sistema.blog.sistemablogspringbootapirest.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private long id;
    @NotEmpty(message = "the name cannot be empty")
    private String name;

    @Email
    private String email;
    private String content;
}
