package com.sistema.blog.sistemablogspringbootapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;
import com.sistema.blog.sistemablogspringbootapirest.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    
    @GetMapping
    public List <PostDTO> listPost(){
        return postService.listPosts();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable(name = "id") long id, @RequestBody PostDTO postDTO) {
        PostDTO postResponse = postService.updatePost(id, postDTO);
        return new ResponseEntity<>(postResponse, HttpStatus.OK) ;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post eliminado con éxito", HttpStatus.OK);
    }
    
}
