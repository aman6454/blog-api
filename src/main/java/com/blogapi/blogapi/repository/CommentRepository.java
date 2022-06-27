package com.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
