package com.example.blogging.service.impl;

import com.example.blogging.entity.Category;
import com.example.blogging.entity.Post;
import com.example.blogging.entity.Users;
import com.example.blogging.exceptions.CategoryNotFound;
import com.example.blogging.exceptions.PostNotFoundException;
import com.example.blogging.exceptions.UserNotFound;
import com.example.blogging.payloads.PostDto;
import com.example.blogging.payloads.PostResponse;
import com.example.blogging.repository.CategoryRepository;
import com.example.blogging.repository.PostRepository;
import com.example.blogging.repository.UserReposiory;
import com.example.blogging.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserReposiory userReposiory;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        Users user = userReposiory.findById(userId).orElseThrow(() -> new UserNotFound("User Not Found!!"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFound("User Not Found!!"));
        Post post = mapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post = postRepository.save(post);
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post Not Found with id : " + id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());
        post = postRepository.save(post);
        return mapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);
        postRepository.delete(post);


    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> posts = page.getContent();
        List<PostDto> postsDto = posts.stream().map(p -> mapper.map(p, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContents(postsDto);
        postResponse.setPageSize(pageSize);
        postResponse.setPageNumber(pageNumber);
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLast(page.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post Not Found with id : " + postId));
        PostDto dto = mapper.map(post, PostDto.class);
        return dto;
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFound("Category not found"));
        List<Post> post = postRepository.findByCategory(category);
        return post.stream().map((p) -> mapper.map(p, PostDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<PostDto> getPostByUser(int userId) {
        Users user = userReposiory.findById(userId).orElseThrow(() -> new UserNotFound("User not found"));
        List<Post> post = postRepository.findByUser(user);
        return post.stream().map((p) -> mapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
