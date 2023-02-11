package com.sistema.blog.sistemablogspringbootapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.CommentDTO;
import com.sistema.blog.sistemablogspringbootapirest.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("posts/{postId}/comments")
    public List<CommentDTO> listComments(@PathVariable long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable long postId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId, CommentDTO requestComment){
        CommentDTO commentDTO = commentService.updateComment(postId,commentId,requestComment);
        return new ResponseEntity<>(commentDTO,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment successfully deleted", HttpStatus.OK);
    }
}
