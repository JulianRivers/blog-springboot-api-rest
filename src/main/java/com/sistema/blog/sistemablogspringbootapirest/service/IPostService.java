package com.sistema.blog.sistemablogspringbootapirest.service;

import java.util.List;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;

public interface IPostService {
    
    public PostDTO createPost(PostDTO postDTO);

    public List<PostDTO> listPosts();
}
