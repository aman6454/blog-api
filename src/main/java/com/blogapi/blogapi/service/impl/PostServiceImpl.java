package com.blogapi.blogapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.model.Category;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.model.User;
import com.blogapi.blogapi.payload.PostDto;
import com.blogapi.blogapi.payload.PostPageResponse;
import com.blogapi.blogapi.repository.CategoryRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepository.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
			     .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatePost = this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId)
	     .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
         this.postRepository.delete(post);
	}

	@Override
	public PostPageResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());
		PostPageResponse postPageResponse = new PostPageResponse();
		postPageResponse.setContent(postDto);
		postPageResponse.setPageNumber(pagePost.getNumber());
		postPageResponse.setPageSize(pagePost.getSize());
		postPageResponse.setTotalElements(pagePost.getTotalElements());
		postPageResponse.setTotalPages(pagePost.getTotalPages());
		postPageResponse.setLastPages(pagePost.isLast());
		return postPageResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	    Post postById = this.postRepository.findById(postId)
	    		.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(postById, PostDto.class);
	}

	@Override
	public PostPageResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Page<Post> pagePost = this.postRepository.findByCategory(category,pageable);
		List<Post> posts = pagePost.getContent();
		 List<PostDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		 PostPageResponse postPageResponse = new PostPageResponse();
			postPageResponse.setContent(postDto);
			postPageResponse.setPageNumber(pagePost.getNumber());
			postPageResponse.setPageSize(pagePost.getSize());
			postPageResponse.setTotalElements(pagePost.getTotalElements());
			postPageResponse.setTotalPages(pagePost.getTotalPages());
			postPageResponse.setLastPages(pagePost.isLast());
			return postPageResponse;
	}

	@Override
	public PostPageResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		Page<Post> pagePost = this.postRepository.findByUser(user,pageable);
		List<Post> posts = pagePost.getContent();
		 List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		 PostPageResponse postPageResponse = new PostPageResponse();
			postPageResponse.setContent(postDto);
			postPageResponse.setPageNumber(pagePost.getNumber());
			postPageResponse.setPageSize(pagePost.getSize());
			postPageResponse.setTotalElements(pagePost.getTotalElements());
			postPageResponse.setTotalPages(pagePost.getTotalPages());
			postPageResponse.setLastPages(pagePost.isLast());
			return postPageResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	  List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
		return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();
	}

}
