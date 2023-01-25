package com.sistema.blog.sistemablogspringbootapirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringbootapirest.exception.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringbootapirest.model.dto.CommentDTO;
import com.sistema.blog.sistemablogspringbootapirest.model.entity.Comment;
import com.sistema.blog.sistemablogspringbootapirest.model.entity.Post;
import com.sistema.blog.sistemablogspringbootapirest.respository.CommentRepository;
import com.sistema.blog.sistemablogspringbootapirest.respository.PostRepository;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private CommentDTO mapCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setContent(comment.getContent());
        commentDTO.setEmail(comment.getEmail());
        return commentDTO;
    }

    private Comment mapComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setContent(commentDTO.getContent());
        comment.setEmail(commentDTO.getEmail());
        return comment;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = mapComment(commentDTO);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapCommentDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPost(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapCommentDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        return null;


    }

}
