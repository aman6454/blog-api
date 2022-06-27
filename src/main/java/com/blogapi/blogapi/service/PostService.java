package com.blogapi.blogapi.service;

import java.util.List;

import com.blogapi.blogapi.payload.PostDto;
import com.blogapi.blogapi.payload.PostPageResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostPageResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(Integer postId);
	PostPageResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostPageResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	List<PostDto> searchPosts(String keyword);
}
