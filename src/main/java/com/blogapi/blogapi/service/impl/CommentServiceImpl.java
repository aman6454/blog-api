package com.blogapi.blogapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.model.Comment;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.model.User;
import com.blogapi.blogapi.payload.CommentDto;
import com.blogapi.blogapi.repository.CommentRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.service.CommentService;

@Service	
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
	Post post = this.postRepository.findById(postId)
			.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
	User user = this.userRepository.findById(userId)
			.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
	 Comment comment = this.modelMapper.map(commentDto, Comment.class);
	 comment.setContent(commentDto.getContent());
	 comment.setPost(post);
	 comment.setUser(user);
	 Comment saveComment = this.commentRepository.save(comment);
	return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
             this.commentRepository.delete(comment);
		
	}

}
