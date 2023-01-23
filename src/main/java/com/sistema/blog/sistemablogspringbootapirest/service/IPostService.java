package com.sistema.blog.sistemablogspringbootapirest.service;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;
import com.sistema.blog.sistemablogspringbootapirest.model.response.PostResponse;

public interface IPostService {
    
    public PostDTO createPost(PostDTO postDTO);

    public PostResponse listPosts(int pageNo, int pageSize, String orderBy, String sortDir);

    public PostDTO getPostById(long id);

    public PostDTO updatePost(long id, PostDTO postDTO);

    public void deletePost(long id);
}
