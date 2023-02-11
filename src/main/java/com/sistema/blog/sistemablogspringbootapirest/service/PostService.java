package com.sistema.blog.sistemablogspringbootapirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringbootapirest.exception.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;
import com.sistema.blog.sistemablogspringbootapirest.model.entity.Post;
import com.sistema.blog.sistemablogspringbootapirest.model.response.PostResponse;
import com.sistema.blog.sistemablogspringbootapirest.respository.PostRepository;

@Service
public class PostService implements IPostService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapPost(postDTO);
        Post newPost = postRepository.save(post);
        return mapPostDTO(newPost);
    }

    @Override
    public PostResponse listPosts(int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
        Sort.by(orderBy).ascending():
        Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        
        List<Post> listPosts = posts.getContent();
        List<PostDTO> content = listPosts.stream()
                                .map(post -> mapPostDTO(post))
                                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
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
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        return postDTO;
    }

    // change Post DTO to Post Entity
    private Post mapPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        return post;
    }

}
