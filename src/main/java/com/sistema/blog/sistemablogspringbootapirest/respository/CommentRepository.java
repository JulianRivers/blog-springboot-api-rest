package com.sistema.blog.sistemablogspringbootapirest.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.sistemablogspringbootapirest.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    public List<Comment> findByPostId(long postId);
}
