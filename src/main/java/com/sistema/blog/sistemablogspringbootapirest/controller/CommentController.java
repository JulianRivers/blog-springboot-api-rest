package com.sistema.blog.sistemablogspringbootapirest.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.CommentDTO;
import com.sistema.blog.sistemablogspringbootapirest.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @GetMapping("posts/{postId}/comments")
    public List<CommentDTO> listComments(@PathVariable long postId){
        return commentService.getCommentsByPost(postId);
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable long postId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

}
