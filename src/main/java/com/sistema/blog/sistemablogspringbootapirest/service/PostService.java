package com.sistema.blog.sistemablogspringbootapirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringbootapirest.exception.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;
import com.sistema.blog.sistemablogspringbootapirest.model.entity.Post;
import com.sistema.blog.sistemablogspringbootapirest.respository.PostRepository;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapPost(postDTO);
        Post newPost = postRepository.save(post);
        return mapPostDTO(newPost);
    }

    @Override
    public List<PostDTO> listPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapPostDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        return mapPostDTO(post);
    }

    @Override
    public PostDTO updatePost(long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post UpdatedPost = postRepository.save(post);
        return mapPostDTO(UpdatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepository.delete(post);
    }

     // change Post entity to Post DTO
     private PostDTO mapPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    // change Post DTO to Post Entity
    private Post mapPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }

}
