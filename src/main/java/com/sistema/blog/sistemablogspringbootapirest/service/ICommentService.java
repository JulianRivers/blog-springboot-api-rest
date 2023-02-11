package com.sistema.blog.sistemablogspringbootapirest.service;

import java.util.List;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.CommentDTO;

public interface ICommentService {
    public CommentDTO createComment(long postId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPost(long postId);

    public CommentDTO getCommentById(long postId, long commentId);

    public CommentDTO updateComment(long postId, long commentId, CommentDTO requestComment);

    public void deleteComment(long postId, long commentId);
}
